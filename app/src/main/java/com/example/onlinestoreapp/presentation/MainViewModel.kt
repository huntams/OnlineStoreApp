package com.example.onlinestoreapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.data.model.User
import com.example.onlinestoreapp.domain.db.GetUserDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserDBUseCase: GetUserDBUseCase
) : ViewModel() {
    private val _userLiveData = MutableLiveData<User?>()
    val userLiveData: LiveData<User?> = _userLiveData

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            getUserDBUseCase().collect {
                _userLiveData.postValue(it)
            }
        }
    }
}