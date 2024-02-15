package com.example.network.remote

import com.example.network.remote.model.ApiItems
import retrofit2.http.GET

interface StoreApiService {

    @GET("97e721a7-0a66-4cae-b445-83cc0bcf9010")
    suspend fun getCatalog(): ApiItems
}