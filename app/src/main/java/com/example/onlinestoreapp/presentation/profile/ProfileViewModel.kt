package com.example.onlinestoreapp.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.data.db.StoreDB
import com.example.onlinestoreapp.data.model.ResultLoader
import com.example.onlinestoreapp.data.model.User
import com.example.onlinestoreapp.data.remote.model.ApiProduct
import com.example.onlinestoreapp.domain.WordDeclensionUseCase
import com.example.onlinestoreapp.domain.db.DeleteProductDBUseCase
import com.example.onlinestoreapp.domain.db.GetProductsDBUseCase
import com.example.onlinestoreapp.domain.db.GetUserDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val db: StoreDB,
    private val getProductsUseCase: GetProductsDBUseCase,
    private val deleteProductDBUseCase: DeleteProductDBUseCase,
    private val getUserDBUseCase: GetUserDBUseCase,
    private val wordDeclensionUseCase: WordDeclensionUseCase,
) : ViewModel() {
    private val _productsLiveData = MutableLiveData<List<ApiProduct>>()
    val productsLiveData: LiveData<List<ApiProduct>> = _productsLiveData

    private val _deleteLiveData = MutableLiveData<ResultLoader<Int>>()
    val deleteLiveData: LiveData<ResultLoader<Int>> = _deleteLiveData
    private val _userLiveData = MutableLiveData<User?>()
    val userLiveData: LiveData<User?> = _userLiveData
    init {
        getProducts()
    }

    fun deleteDB() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _deleteLiveData.postValue(ResultLoader.Loading())
                db.clearAllTables()
                _deleteLiveData.postValue(ResultLoader.Success(0))
            } catch (t: Throwable) {
                _deleteLiveData.postValue(ResultLoader.Failure(t))
            }

        }

    }
    fun wordDeclension(num: Int, data: String): String {
        return wordDeclensionUseCase(num, data)
    }

    fun getUser(){
        viewModelScope.launch(Dispatchers.IO) {
            getUserDBUseCase().collect {
                _userLiveData.postValue(it)
            }
        }
    }
    private fun getProducts() {
        viewModelScope.launch {
            getProductsUseCase().collect { list ->
                list.map {
                    it.like = true
                }
                _productsLiveData.postValue(list)
            }
        }
    }

    fun deleteProduct(product: ApiProduct) {
        viewModelScope.launch {
            deleteProductDBUseCase(product)
        }
    }

}