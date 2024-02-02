package com.example.onlinestoreapp.domain.db

import com.example.onlinestoreapp.data.db.repository.StoreDBRepository
import com.example.onlinestoreapp.data.remote.model.ApiProduct
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsDBUseCase @Inject constructor(
    private val storeDBRepository: StoreDBRepository
) {
    operator fun invoke(): Flow<List<ApiProduct>> {
        return storeDBRepository.getProducts()
    }
}