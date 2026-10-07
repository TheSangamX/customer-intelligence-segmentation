package com.sangamgupta.customerintelligence.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF8AB4FF),
    onPrimary = Color(0xFF102C58),
    secondary = Color(0xFFB8C8E3),
    tertiary = Color(0xFFB9C7DF),
    background = Color(0xFF101827),
    onBackground = Color(0xFFE8EEF8),
    surface = Color(0xFF182337),
    onSurface = Color(0xFFE8EEF8),
    surfaceVariant = Color(0xFF26344A),
    onSurfaceVariant = Color(0xFFB6C2D4),
    secondaryContainer = Color(0xFF263A57),
    onSecondaryContainer = Color(0xFFE6EEFC),
    outline = Color(0xFF53637B),
    outlineVariant = Color(0xFF35445A),
    error = Color(0xFFFFB4AB)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF276EF1),
    onPrimary = Color.White,
    secondary = Color(0xFF52647E),
    tertiary = Color(0xFF52647E),
    background = Color(0xFFF6F8FC),
    onBackground = Color(0xFF172B4D),
    surface = Color.White,
    onSurface = Color(0xFF172B4D),
    surfaceVariant = Color(0xFFEDF1F7),
    onSurfaceVariant = Color(0xFF667085),
    secondaryContainer = Color(0xFFF0F5FF),
    onSecondaryContainer = Color(0xFF172B4D),
    outline = Color(0xFF8993A4),
    outlineVariant = Color(0xFFE1E6EF),
    error = Color(0xFFB42318)
)

@Composable
fun CustomerIntelligenceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
