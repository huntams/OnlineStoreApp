package com.example.onlinestoreapp.domain

import com.example.onlinestoreapp.data.model.Items
import com.example.onlinestoreapp.data.remote.repository.StoreRepository
import javax.inject.Inject

class GetCatalogUseCase @Inject constructor(
    private val repository: StoreRepository,
) {
    suspend operator fun invoke(): Items {
        return repository.getCatalog()
    }
}