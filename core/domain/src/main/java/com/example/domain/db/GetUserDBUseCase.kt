package com.example.domain.db

import com.example.data.repository.StoreDBRepository
import com.example.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDBUseCase @Inject constructor(
    private val storeDBRepository: StoreDBRepository
) {
    operator fun invoke(): Flow<User?> {
        return storeDBRepository.getUser()
    }
}