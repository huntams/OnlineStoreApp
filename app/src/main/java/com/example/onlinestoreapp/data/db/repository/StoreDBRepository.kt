package com.example.onlinestoreapp.data.db.repository

import com.example.onlinestoreapp.data.remote.model.ApiProduct
import kotlinx.coroutines.flow.Flow

interface StoreDBRepository {
    suspend fun addProduct(product: ApiProduct)
    fun getProducts(): Flow<List<ApiProduct>>

    suspend fun addUser(name: String, surname: String, phone:Long)
}