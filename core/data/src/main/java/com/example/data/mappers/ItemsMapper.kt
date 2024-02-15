package com.example.data.mappers

import com.example.model.Items
import com.example.network.remote.model.ApiItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemsMapper @Inject constructor(
    private val productMapper: ProductMapper
) {
    fun apiToModel(apiItems: ApiItems) = Items(
        items = apiItems.items.map {
            productMapper.fromUIModelToEntity(
                it
            )
        }
    )
}