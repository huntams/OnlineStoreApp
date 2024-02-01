package com.example.onlinestoreapp.presentation.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.data.model.Items
import com.example.onlinestoreapp.data.remote.model.ApiProduct
import com.example.onlinestoreapp.domain.FilterChipUseCase
import com.example.onlinestoreapp.domain.GetCatalogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val filterChipUseCase: FilterChipUseCase,
) : ViewModel() {
    private val _catalogLiveData = MutableLiveData<Items>()
    val catalogLiveData: LiveData<Items> = _catalogLiveData
    private val _filterLiveData = MutableLiveData<List<ApiProduct>>()
    val filterLiveData: LiveData<List<ApiProduct>> = _filterLiveData

    init {
        getCatalog()
    }
    fun filterData(listData: List<ApiProduct>,filterData: String){
        viewModelScope.launch {
            _filterLiveData.postValue(filterChipUseCase(listData,filterData))
        }
    }
    private fun getCatalog(){
        viewModelScope.launch {
            getCatalogUseCase().also {
                _catalogLiveData.postValue(it)
            }
        }
    }
}