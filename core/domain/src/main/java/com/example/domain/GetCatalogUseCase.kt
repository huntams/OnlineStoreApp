package com.example.domain

import com.example.data.repository.StoreRepository
import com.example.model.Items
import javax.inject.Inject

class GetCatalogUseCase @Inject constructor(
    private val repository: StoreRepository,
) {
    suspend operator fun invoke(): Items {
        return repository.getCatalog()
    }
}