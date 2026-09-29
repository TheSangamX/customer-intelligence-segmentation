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