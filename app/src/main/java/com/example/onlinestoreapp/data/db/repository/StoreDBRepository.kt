package com.example.onlinestoreapp.data.db.repository

import com.example.onlinestoreapp.data.model.User
import com.example.onlinestoreapp.data.remote.model.ApiProduct
import kotlinx.coroutines.flow.Flow

interface StoreDBRepository {
    suspend fun addProduct(product: ApiProduct)
    fun getProducts(): Flow<List<ApiProduct>>

    suspend fun deleteProduct(product: ApiProduct)
    suspend fun likeProduct(product: ApiProduct)
    fun getUser(): Flow<User?>
    suspend fun addUser(name: String, surname: String, phone: String)
}