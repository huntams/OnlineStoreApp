package com.example.onlinestoreapp.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.domain.db.AddUserDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val addUserDBUseCase: AddUserDBUseCase
) : ViewModel() {

    fun addUser(name: String, surname: String, phone: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            addUserDBUseCase(name, surname, phone)
        }
    }
}