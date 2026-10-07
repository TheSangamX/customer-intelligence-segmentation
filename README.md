# Customer Intelligence & Segmentation

An end-to-end customer segmentation system built using Unsupervised Machine Learning, RFM analysis, K-Means clustering, FastAPI, and Streamlit.

The system analyzes customer purchasing behavior and assigns customers to meaningful business segments based on:

- Recency
- Frequency
- Monetary Value

---

## Project Overview

Customer segmentation helps businesses understand different types of customers based on their purchasing behavior.

This project uses RFM analysis and K-Means clustering to divide customers into behavioral segments.

The project also compares multiple clustering approaches:

- K-Means
- Hierarchical Clustering
- DBSCAN

## Android app and API connection

Start the FastAPI server from the project root so it accepts connections from an Android device:

```bash
uvicorn backend.main:app --host 0.0.0.0 --port 8000
```

The Android app defaults to this PC's current Wi-Fi address, `http://192.168.0.112:8000/`, for physical-phone testing. If the PC's address changes, rebuild with its current LAN IPv4 address. For the Android Emulator, use its host alias instead:

```bash
cd android-app
./gradlew assembleDebug -PAPI_BASE_URL=http://10.0.2.2:8000/
```

For a physical phone, both devices must be on the same network and Windows Firewall must allow inbound connections to port 8000 on the private network. Set `API_BASE_URL` to the PC's current LAN address if it changes.

PCA is used for dimensionality reduction and visualization.

---

## Customer Segments

The final K-Means model identifies three customer segments:

### 1. High-Value / Loyal

Customers with:

- Low recency
- High purchase frequency
- High monetary value

Possible business actions:

- Loyalty programs
- Premium offers
- Personalized rewards
- Customer retention strategies

### 2. Inactive / Low-Value

Customers with:

- High recency
- Low purchase frequency
- Low monetary value

Possible business actions:

- Re-engagement campaigns
- Personalized promotions
- Win-back offers

### 3. Regular / Mid-Value

Customers with:

- Moderate recency
- Moderate purchase frequency
- Moderate monetary value

Possible business actions:

- Cross-selling
- Personalized promotions
- Engagement campaigns

---

## Machine Learning Workflow

```text
Raw Transaction Data
        ↓
Data Cleaning
        ↓
Exploratory Data Analysis
        ↓
RFM Feature Engineering
        ↓
Feature Scaling
        ↓
K-Means Clustering
        ↓
Hierarchical Clustering
        ↓
DBSCAN
        ↓
Silhouette Score Comparison
        ↓
PCA Visualization
        ↓
Customer Segment Interpretation
        ↓
Model Saving
        ↓
FastAPI
        ↓
Streamlit Web Application
