package com.example.onlinestoreapp.domain.db

import com.example.onlinestoreapp.data.db.repository.StoreDBRepository
import com.example.onlinestoreapp.data.remote.model.ApiProduct
import javax.inject.Inject

class DeleteProductDBUseCase @Inject constructor(
    private val storeDBRepository: StoreDBRepository
) {
    suspend operator fun invoke(product: ApiProduct) {
        storeDBRepository.deleteProduct(product)
    }
}