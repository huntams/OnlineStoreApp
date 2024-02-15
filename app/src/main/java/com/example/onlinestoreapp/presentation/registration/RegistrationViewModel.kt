package com.example.onlinestoreapp.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.db.AddUserDBUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class RegistrationViewModel @Inject constructor(
    private val addUserDBUseCase: AddUserDBUseCase
) : ViewModel() {

    fun addUser(name: String, surname: String, phone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addUserDBUseCase(name, surname, phone)
        }
    }
}