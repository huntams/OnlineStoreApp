package com.example.onlinestoreapp.presentation.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.data.model.Items
import com.example.onlinestoreapp.data.remote.model.ApiProduct
import com.example.onlinestoreapp.domain.FilterChipUseCase
import com.example.onlinestoreapp.domain.GetCatalogUseCase
import com.example.onlinestoreapp.domain.WordDeclensionUseCase
import com.example.onlinestoreapp.domain.db.GetProductsDBUseCase
import com.example.onlinestoreapp.domain.db.LikeProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val filterChipUseCase: FilterChipUseCase,
    private val wordDeclensionUseCase: WordDeclensionUseCase,
    private val getProductsDBUseCase: GetProductsDBUseCase,
    private val likeProductUseCase: LikeProductUseCase,
) : ViewModel() {
    private val _catalogLiveData = MutableLiveData<Items>()
    val catalogLiveData: LiveData<Items> = _catalogLiveData
    private val _filterLiveData = MutableLiveData<List<ApiProduct>>()
    val filterLiveData: LiveData<List<ApiProduct>> = _filterLiveData

    init {
        getCatalog()
    }

    fun wordDeclension(num: Int, data: String): String {
        return wordDeclensionUseCase(num, data)
    }

    fun likeProduct(product: ApiProduct) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!product.like)
                product.like = !product.like

            likeProductUseCase(product)
        }
        getCatalog()

    }

    fun filterData(listData: List<ApiProduct>, filterData: String): List<ApiProduct> {
        return filterChipUseCase(listData, filterData)

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
