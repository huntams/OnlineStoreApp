package com.example.domain.db

import com.example.data.repository.StoreDBRepository
import com.example.model.Product
import javax.inject.Inject

class LikeProductUseCase @Inject constructor(
    private val storeDBRepository: StoreDBRepository
) {
    suspend operator fun invoke(product: Product) {
        storeDBRepository.likeProduct(product)
    }
}