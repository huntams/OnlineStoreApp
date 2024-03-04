package com.example.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.StoreDB
import com.example.domain.WordDeclensionUseCase
import com.example.domain.db.DeleteProductDBUseCase
import com.example.domain.db.GetProductsDBUseCase
import com.example.domain.db.GetUserDBUseCase
import com.example.model.Product
import com.example.model.ResultLoader
import com.example.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class ProfileViewModel @Inject constructor(
    private val db: StoreDB,
    private val getProductsUseCase: GetProductsDBUseCase,
    private val deleteProductDBUseCase: DeleteProductDBUseCase,
    private val getUserDBUseCase: GetUserDBUseCase,
    private val wordDeclensionUseCase: WordDeclensionUseCase,
) : ViewModel() {
    private val _productsLiveData = MutableLiveData<List<Product>>()
    val productsLiveData: LiveData<List<Product>> = _productsLiveData

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

    fun wordDeclension(num: Int, data: List<String>): String {
        return wordDeclensionUseCase(num, data)
    }

    fun getUser() {
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

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            deleteProductDBUseCase(product)
        }
    }

}