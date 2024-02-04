package com.example.onlinestoreapp.domain

import android.content.res.Resources
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.data.remote.model.ApiProduct
import javax.inject.Inject

class FilterChipUseCase @Inject constructor(
    private val resources: Resources
) {
    operator fun invoke(listData: List<ApiProduct>, filterData: String): List<ApiProduct> {
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
}