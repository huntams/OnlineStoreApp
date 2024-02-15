package com.example.data.repository

import com.example.model.Product
import com.example.model.User
import kotlinx.coroutines.flow.Flow

interface StoreDBRepository {
    suspend fun addProduct(product: Product)
    fun getProducts(): Flow<List<Product>>

    suspend fun deleteProduct(product: Product)
    suspend fun likeProduct(product: Product)
    fun getUser(): Flow<User?>
    suspend fun addUser(name: String, surname: String, phone: String)
}