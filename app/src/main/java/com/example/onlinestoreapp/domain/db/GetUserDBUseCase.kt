package com.example.onlinestoreapp.domain.db

import com.example.onlinestoreapp.data.db.repository.StoreDBRepository
import com.example.onlinestoreapp.data.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDBUseCase @Inject constructor(
    private val storeDBRepository: StoreDBRepository
) {
    operator fun invoke(): Flow<User?> {
        return storeDBRepository.getUser()
    }
}