package com.example.onlinestoreapp.domain

import com.example.onlinestoreapp.data.remote.model.ApiProduct
import javax.inject.Inject

class FilterChipUseCase @Inject constructor(
) {
    operator fun invoke(listData: List<ApiProduct>, filterData: String) : List<ApiProduct> {
        return listData.filter {
            it.tags.contains(filterData)
        }
    }
}