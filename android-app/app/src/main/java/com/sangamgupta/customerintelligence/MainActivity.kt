package com.sangamgupta.customerintelligence

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sangamgupta.customerintelligence.ui.theme.CustomerIntelligenceTheme
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val preferences = remember { context.getSharedPreferences("customer_intelligence_settings", MODE_PRIVATE) }
            var darkMode by rememberSaveable {
                mutableStateOf(preferences.getBoolean("dark_mode", false))
            }
            var showSettings by rememberSaveable { mutableStateOf(false) }
            CustomerIntelligenceTheme(darkTheme = darkMode, dynamicColor = false) {
                if (showSettings) {
                    SettingsScreen(
                        darkMode = darkMode,
                        onDarkModeChanged = { enabled ->
                            darkMode = enabled
                            preferences.edit().putBoolean("dark_mode", enabled).apply()
                        },
                        onBack = { showSettings = false }
                    )
                } else {
                    CustomerIntelligenceScreen(onOpenSettings = { showSettings = true })
                }
            }
        }
    }
}

@Composable
private fun CustomerIntelligenceScreen(onOpenSettings: () -> Unit) {
    var recency by remember { mutableStateOf("") }
    var frequency by remember { mutableStateOf("") }
    var monetary by remember { mutableStateOf("") }
    var response by remember { mutableStateOf<PredictionResponse?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    var submitted by remember { mutableStateOf(false) }
    var recencyTouched by remember { mutableStateOf(false) }
    var frequencyTouched by remember { mutableStateOf(false) }
    var monetaryTouched by remember { mutableStateOf(false) }
    val recencyError = validateRecency(recency)
    val frequencyError = validateFrequency(frequency)
    val monetaryError = validateMonetary(monetary)
    val formValid = recencyError == null && frequencyError == null && monetaryError == null
    val colors = MaterialTheme.colorScheme
    val ink = colors.onSurface
    val muted = colors.onSurfaceVariant
    val accent = colors.primary

    Scaffold(containerColor = colors.background) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 26.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                TextButton(onClick = onOpenSettings) { Text("Settings", color = accent) }
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("CUSTOMER ANALYTICS", color = accent, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.4.sp)
                Text("Customer Intelligence", color = ink, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Text(
                    "Analyze customer behavior using RFM analysis and K-Means clustering.",
                    color = muted,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = colors.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Column(Modifier.fillMaxWidth().padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text("Customer RFM Information", color = ink, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                    RfmInput("Recency (days)", recency, {
                        recency = it
                        recencyTouched = true
                    }, "Days since last purchase", (recencyTouched || submitted).thenError(recencyError))
                    RfmInput("Frequency (purchases)", frequency, {
                        frequency = it
                        frequencyTouched = true
                    }, "Number of purchases", (frequencyTouched || submitted).thenError(frequencyError))
                    RfmInput("Monetary (total spending)", monetary, {
                        monetary = it
                        monetaryTouched = true
                    }, "Total customer spending", (monetaryTouched || submitted).thenError(monetaryError), decimal = true)

                    if (error != null) {
                        Text(error.orEmpty(), color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
                    }
                    Button(
                        onClick = {
                            submitted = true
                            error = null
                            if (!formValid) return@Button
                            response = null
                            loading = true
                            scope.launch {
                                try {
                                    response = ApiClient.service.predictSegment(
                                        CustomerData(
                                            recency = recency.toInt(),
                                            frequency = frequency.toInt(),
                                            monetary = monetary.toDouble()
                                        )
                                    )
                                } catch (exception: IOException) {
                                    error = "Can't reach the API at ${BuildConfig.API_BASE_URL}. Start FastAPI and confirm this device can access your PC."
                                } catch (exception: Exception) {
                                    error = "Prediction failed. Please check your values and try again."
                                } finally {
                                    loading = false
                                }
                            }
                        },
                        enabled = !loading,
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        shape = MaterialTheme.shapes.medium,
                        colors = ButtonDefaults.buttonColors(containerColor = accent)
                    ) {
                        if (loading) CircularProgressIndicator(modifier = Modifier.height(22.dp), color = Color.White, strokeWidth = 2.dp)
                        else Text("Predict Customer Segment", fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            response?.let { result ->
                val insights = segmentInsights(result.segment)
                Card(
                    colors = CardDefaults.cardColors(containerColor = colors.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    shape = MaterialTheme.shapes.large
                ) {
                    Column(Modifier.fillMaxWidth().padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text("Customer Segment", color = muted, style = MaterialTheme.typography.labelLarge)
                            Text(result.segment, color = ink, fontSize = 23.sp, fontWeight = FontWeight.Bold)
                            Text("Cluster ${result.cluster}", color = accent, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                        }
                        Hairline()
                        Text("RFM Summary", color = ink, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
                        SummaryRow("Recency", "$recency days")
                        SummaryRow("Frequency", "$frequency purchases")
                        SummaryRow("Monetary", "₹${"%.2f".format(monetary.toDouble())}")
                        Hairline()
                        Text("Segment Insights", color = ink, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
                        Text(insights.first, color = muted, style = MaterialTheme.typography.bodyMedium)
                        Surface(color = colors.secondaryContainer, shape = MaterialTheme.shapes.medium) {
                            Column(Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                                Text("RECOMMENDATION", color = accent, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                                Text(insights.second, color = colors.onSecondaryContainer, style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(4.dp))
        }
    }
}

@Composable
private fun RfmInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    supporting: String,
    validationError: String?,
    decimal: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(label) },
        isError = validationError != null,
        supportingText = {
            Text(
                validationError ?: supporting,
                color = if (validationError != null) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = if (decimal) KeyboardType.Decimal else KeyboardType.Number),
        shape = MaterialTheme.shapes.medium
    )
}

@Composable
private fun SummaryRow(label: String, value: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
        Text(value, color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun Hairline() {
    Spacer(Modifier.fillMaxWidth().height(1.dp).background(MaterialTheme.colorScheme.outlineVariant))
}

private fun Boolean.thenError(error: String?): String? = if (this) error else null

private fun validateRecency(value: String): String? {
    if (value.isBlank()) return "Recency is required."
    val days = value.toIntOrNull() ?: return "Enter a whole number of days."
    return when {
        days <= 0 -> "Recency must be greater than 0 days."
        days > 3650 -> "Recency must be 3,650 days or less."
        else -> null
    }
}

private fun validateFrequency(value: String): String? {
    if (value.isBlank()) return "Frequency is required."
    val purchases = value.toIntOrNull() ?: return "Enter a whole number of purchases."
    return when {
        purchases <= 0 -> "Frequency must be at least 1 purchase."
        purchases > 1000 -> "Frequency must be 1,000 purchases or less."
        else -> null
    }
}

private fun validateMonetary(value: String): String? {
    if (value.isBlank()) return "Monetary value is required."
    val spending = value.toDoubleOrNull() ?: return "Enter a valid spending amount."
    return when {
        !spending.isFinite() -> "Enter a finite spending amount."
        spending <= 0 -> "Spending must be greater than ₹0."
        spending > 10_000_000 -> "Spending must be ₹10,000,000 or less."
        else -> null
    }
}

private fun segmentInsights(segment: String): Pair<String, String> = when (segment.trim().lowercase()) {
    "high-value / loyal" -> "This customer shows strong purchasing activity and high monetary value. They can be considered an important and loyal customer segment." to
        "Focus on retention, loyalty programs, premium offers and personalized rewards."
    "regular / mid-value" -> "This customer shows moderate purchasing activity and spending behavior." to
        "Use personalized promotions and cross-selling strategies to increase engagement and spending."
    "low-value / occasional" -> "This customer shows relatively low purchasing activity and spending." to
        "Use targeted promotions and re-engagement campaigns to encourage repeat purchases."
    "inactive / at-risk" -> "This customer has low recent activity and may be at risk of becoming inactive." to
        "Use reactivation campaigns, discounts and personalized offers."
    else -> "This customer has been assigned to the ${segment.ifBlank { "unclassified" }} segment based on the model's RFM analysis." to
        "Review this customer's recent activity and tailor engagement based on their purchase history."
}
