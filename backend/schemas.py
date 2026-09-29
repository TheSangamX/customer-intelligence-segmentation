from pydantic import BaseModel


class CustomerData(BaseModel):
    recency: float
    frequency: float
    monetary: float
    