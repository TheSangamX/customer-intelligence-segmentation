from pydantic import BaseModel

# Define the input validation model for the API request
class CustomerData(BaseModel):
    recency: float
    frequency: float
    monetary: float

# Define the output validation model for the API response
class PredictionResponse(BaseModel):
    cluster: int
    segment: str