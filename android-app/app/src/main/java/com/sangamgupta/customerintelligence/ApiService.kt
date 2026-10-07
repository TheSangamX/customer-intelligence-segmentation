package com.sangamgupta.customerintelligence

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("predict")
    suspend fun predictSegment(@Body customerData: CustomerData): PredictionResponse
}
