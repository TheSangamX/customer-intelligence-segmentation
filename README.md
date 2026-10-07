# Customer Intelligence & Segmentation

An end-to-end customer segmentation project that turns transaction records into customer-level Recency, Frequency and Monetary (RFM) features, assigns a K-Means segment, and returns the result through a FastAPI service. The project includes a Jupyter notebook for the analysis, a Streamlit web app, and a Kotlin Android app.

## Project overview

RFM analysis summarizes customer behavior using three measures:

| Measure | Meaning | Typical interpretation |
| --- | --- | --- |
| **Recency** | Days since the customer's most recent purchase | Lower values mean a more recent purchase. |
| **Frequency** | Number of transaction records associated with the customer | Higher values indicate more purchase activity. |
| **Monetary** | Sum of the customer's transaction amounts | Higher values indicate greater recorded spending. |

The notebook aggregates these measures by customer, scales the three features, and fits a three-cluster K-Means model. FastAPI loads the saved scaler, K-Means model, and cluster-name mapping to classify new RFM values. Streamlit and Android provide user interfaces for submitting values and reading the prediction.

## Features

- Customer-level RFM feature engineering from transaction data.
- Scaled RFM features and K-Means clustering.
- Named customer segments with explanatory insights and business recommendations in the Streamlit app.
- Streamlit RFM summary table and bar chart for a submitted customer.
- FastAPI prediction endpoint shared by the web and Android clients.
- Android Compose interface with inline RFM validation, loading and network-error states, segment details, and an RFM summary.
- Android Settings/About screen with a saved Dark Mode preference and developer information.
- Android launcher icon resources.
- Notebook exploration of clustering alternatives, silhouette scores, and PCA visualizations.

## Machine learning workflow

```text
Transaction CSV
      ↓
Data checks, cleaning, and exploratory analysis
      ↓
Customer-level RFM feature engineering
      ↓
StandardScaler feature scaling
      ↓
K-Means clustering (3 clusters)
      ↓
Cluster profiling and segment interpretation
      ↓
Save scaler, K-Means model, and segment-name mapping
      ↓
FastAPI prediction endpoint
      ├── Streamlit web app
      └── Android app
```

The notebook also explores the elbow method with Kneed, Agglomerative (hierarchical) clustering, DBSCAN, silhouette-score comparisons, and two-component PCA visualizations. These are analysis steps in the notebook; the deployed prediction endpoint uses the saved K-Means model.

## Model and segment labels

The saved K-Means model uses three RFM features after `StandardScaler` transformation. The current notebook fits K-Means with three clusters, `random_state=42`, and `n_init=10`.

The checked-in segment mapping is:

| Cluster | Segment | Business interpretation |
| --- | --- | --- |
| 0 | **High-Value / Loyal** | Strong purchasing activity and monetary value; focus on retention, loyalty programs, premium offers, and personalized rewards. |
| 1 | **Inactive / Low-Value** | Lower purchasing activity; consider reactivation campaigns, targeted offers, and limited-time discounts. |
| 2 | **Regular / Mid-Value** | Moderate purchase activity and spending; use personalized promotions and cross-selling to encourage engagement. |

These labels are interpretations attached to the fitted cluster IDs in `models/segment_names.joblib`; they are not supervised target classes.

## Project structure

```text
.
├── android-app/
│   ├── app/src/main/java/com/sangamgupta/customerintelligence/
│   │   ├── ApiClient.kt
│   │   ├── ApiService.kt
│   │   ├── CustomerData.kt
│   │   ├── MainActivity.kt
│   │   ├── PredictionResponse.kt
│   │   ├── SettingsScreen.kt
│   │   └── ui/theme/
│   ├── app/src/main/AndroidManifest.xml
│   ├── app/src/main/res/             # App icon and Android resources
│   ├── app/build.gradle.kts
│   ├── gradle/libs.versions.toml
│   └── gradlew / gradlew.bat
├── backend/
│   ├── main.py                       # FastAPI application and /predict route
│   └── schemas.py                    # Pydantic request/response models
├── data/
│   └── customer_transactions.csv
├── models/
│   ├── kmeans_model.joblib
│   ├── scaler.joblib
│   └── segment_names.joblib
├── notebook/
│   └── customer_segmentation.ipynb   # Data analysis and model workflow
├── web-app/
│   └── app.py                        # Streamlit client
├── requirements.txt
└── README.md
```

## Technology

- **Data and machine learning:** Python, Pandas, NumPy, scikit-learn, SciPy, Kneed, Joblib, Matplotlib
- **API:** FastAPI, Pydantic, Uvicorn, Requests
- **Web interface:** Streamlit
- **Android:** Kotlin, Jetpack Compose, Material 3, Retrofit, Gson, Gradle
- **Version control:** Git

## Setup

### 1. Clone the repository

```bash
git clone <repository-url>
cd customer-intelligence-segmentation
```

Replace `<repository-url>` with the URL of your Git repository.

### 2. Create a Python environment and install dependencies

```bash
python -m venv .venv
```

Activate it, then install the pinned project requirements:

```powershell
# Windows PowerShell
.\.venv\Scripts\Activate.ps1
```

```bash
# macOS / Linux
source .venv/bin/activate
```

```bash
python -m pip install --upgrade pip
pip install -r requirements.txt
```

### 3. Start the FastAPI backend

Run from the repository root. The backend loads the model files from the relative `models/` directory.

```bash
uvicorn backend.main:app --reload
```

The API is available at `http://127.0.0.1:8000`. Interactive API documentation is at `http://127.0.0.1:8000/docs`.

### 4. Start the Streamlit app

Keep FastAPI running, open another terminal at the repository root, activate the same Python environment, and run:

```bash
streamlit run web-app/app.py
```

The Streamlit client sends requests to `http://127.0.0.1:8000/predict`, so it expects the API to be running on the same computer at that address.

### 5. Open or build the Android app

Open the `android-app/` directory in Android Studio and let Gradle sync. The command-line debug build is:

```powershell
# Windows
cd android-app
.\gradlew.bat assembleDebug
```

```bash
# macOS / Linux
cd android-app
./gradlew assembleDebug
```

The debug APK is written to `android-app/app/build/outputs/apk/debug/app-debug.apk`.

The Android API URL is configurable with the Gradle property `API_BASE_URL`. The current default is `http://192.168.0.112:8000/`, which reflects the development PC's LAN address when this project was configured. For a physical phone, run FastAPI with a LAN-accessible bind address and build with the PC's current IPv4 address:

```bash
uvicorn backend.main:app --host 0.0.0.0 --port 8000
```

```powershell
# Example only: replace the address with the PC's current LAN IPv4 address.
.\gradlew.bat assembleDebug -PAPI_BASE_URL=http://192.168.1.25:8000/
```

Connect the phone and PC to the same network and allow inbound port 8000 through the PC firewall if needed. For an Android Emulator, use its host alias instead:

```bash
./gradlew assembleDebug -PAPI_BASE_URL=http://10.0.2.2:8000/
```

The app uses cleartext HTTP for local development. Use an appropriately secured HTTPS endpoint for a hosted deployment.

## API

### `GET /`

Returns a short message confirming that the API process is running.

### `POST /predict`

The request body is validated against the `CustomerData` Pydantic schema. The API applies the saved scaler, predicts the K-Means cluster, and maps that cluster to its saved segment name.

**Request**

```json
{
  "recency": 10,
  "frequency": 11,
  "monetary": 10000
}
```

**Response**

```json
{
  "cluster": 2,
  "segment": "Regular / Mid-Value"
}
```

The values above are an example request and response for the checked-in model. The request schema represents the RFM values as numbers; the FastAPI schema does not currently enforce the UI's business ranges.

## Applications

### Streamlit

The web app provides numeric inputs for recency, frequency, and monetary value, submits them to FastAPI, and displays the returned cluster and segment. It also presents the submitted RFM values in a summary table, a bar chart, and a segment-specific insight and recommendation. Its input widgets currently constrain recency to 0–1,000, frequency to 1–1,000, and monetary value to 0–10,000,000.

### Android

The Android client is written in Kotlin with Jetpack Compose and Material 3. It sends requests through Retrofit with Gson serialization. It provides inline input errors and does not submit invalid values: recency must be an integer from 1 to 3,650 days, frequency an integer from 1 to 1,000 purchases, and monetary value greater than 0 and no more than 10,000,000. The result screen shows the API-returned segment and cluster, the RFM summary, segment insights, and a recommendation. Settings includes a persistent Dark Mode toggle and project/developer details.

## Screenshots

Screenshots have not been added to the repository yet.

### Streamlit Web App

_Placeholder — add a Streamlit screenshot when available._

### Android App

_Placeholder — add a screenshot of the Android prediction screen when available._

### Customer Segment Result

_Placeholder — add a prediction result screenshot when available._

### Settings/About

_Placeholder — add a screenshot of the Android Settings/About screen when available._

## Future improvements

The following are possible future work and are not implemented in this repository:

- Deploy the API to a hosted environment and configure clients to use it over HTTPS.
- Create and document a signed Android release build.
- Add further customer analytics and dashboard views.
- Add automated validation and API integration coverage.

## Author

**Sangam Gupta**

Email: [contact@sangamgupta.in](mailto:contact@sangamgupta.in)

Website: [sangamgupta.in](https://sangamgupta.in)

## License

No license file is currently included. A license can be added when the project’s distribution terms are decided.
