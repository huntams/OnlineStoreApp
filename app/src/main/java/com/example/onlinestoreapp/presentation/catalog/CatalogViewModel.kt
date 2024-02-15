package com.example.onlinestoreapp.presentation.catalog

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetCatalogUseCase
import com.example.domain.R
import com.example.domain.WordDeclensionUseCase
import com.example.domain.db.GetProductsDBUseCase
import com.example.domain.db.LikeProductUseCase
import com.example.model.Items
import com.example.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class CatalogViewModel @Inject constructor(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val wordDeclensionUseCase: WordDeclensionUseCase,
    private val getProductsDBUseCase: GetProductsDBUseCase,
    private val likeProductUseCase: LikeProductUseCase,
    private val resources: Resources
) : ViewModel() {
    private val _catalogLiveData = MutableLiveData<Items>()
    val catalogLiveData: LiveData<Items> = _catalogLiveData
    private val _filterLiveData = MutableLiveData<List<Product>>()
    val filterLiveData: LiveData<List<Product>> = _filterLiveData

    init {
        getCatalog()
    }

    fun wordDeclension(num: Int, data: List<String>): String {
        return wordDeclensionUseCase(num, data)
    }

    fun likeProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!product.like)
                product.like = !product.like

            likeProductUseCase(product)
        }
        getCatalog()

    }

    fun filterData(listData: List<Product>, filterData: String): List<Product> {
        when (filterData) {
            resources.getString(R.string.face) -> return listData.filter {
                it.tags.contains("face")
            }

            resources.getString(R.string.body) -> return listData.filter {
                it.tags.contains("body")
            }

            resources.getString(R.string.suntan) -> return listData.filter {
                it.tags.contains("suntan")
            }

            resources.getString(R.string.mask) -> return listData.filter {
                it.tags.contains("mask")
            }

            else -> return listData
        }

    }

    private fun getCatalog() {
        viewModelScope.launch(Dispatchers.IO) {
            getCatalogUseCase().also { items ->
                getProductsDBUseCase().collect { list ->
                    list.forEach { product ->
                        items.items.map {
                            if (it.id == product.id) {
                                it.like = true
                            }
                        }
                    }
                    _catalogLiveData.postValue(items)

                }


            }
        }
    }
}
