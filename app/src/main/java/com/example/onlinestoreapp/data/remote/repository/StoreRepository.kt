package com.example.onlinestoreapp.data.remote.repository

import com.example.onlinestoreapp.data.model.Items

interface StoreRepository {
    suspend fun getCatalog(): Items
}