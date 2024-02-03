package com.example.onlinestoreapp.domain.db

import com.example.onlinestoreapp.data.db.repository.StoreDBRepository
import javax.inject.Inject

class AddUserDBUseCase @Inject constructor(
    private val storeDBRepository: StoreDBRepository
) {
    suspend operator fun invoke(name: String, surname: String, phone: String) {
        storeDBRepository.addUser(name, surname, phone)
    }
}