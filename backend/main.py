from fastapi import FastAPI
from backend.schemas import CustomerData

app = FastAPI(
    title="Customer Intelligence API",
    description="Customer segmentation using RFM and K-Means",
    version="1.0.0"
)

#Load the scaler and KMeans model
import joblib
from pathlib import Path

model_dir = Path("models")

scaler = joblib.load(model_dir / "scaler.joblib")
kmeans_model = joblib.load(model_dir / "kmeans_model.joblib")
segment_names = joblib.load(model_dir / "segment_names.joblib")

#Home route to check if the API is running
@app.get("/")
def home():
    return {
        "message": "Customer Intelligence API is running"
    }

# Define the API endpoint for customer segmentation
@app.post("/predict")
def predict_customer(data: CustomerData):

    customer_data = [[
        data.recency,
        data.frequency,
        data.monetary
    ]]

    scaled_data = scaler.transform(customer_data)

    cluster = kmeans_model.predict(scaled_data)[0]

    segment = segment_names[cluster]

    return {
        "cluster": int(cluster),
        "segment": segment
    }
