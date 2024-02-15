package com.example.data.repository

import com.example.model.Items


interface StoreRepository {
    suspend fun getCatalog(): Items
}