package com.example.domain.db

import com.example.data.repository.StoreDBRepository
import com.example.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsDBUseCase @Inject constructor(
    private val storeDBRepository: StoreDBRepository
) {
    operator fun invoke(): Flow<List<Product>> {
        return storeDBRepository.getProducts()
    }
}