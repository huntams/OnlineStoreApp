package com.example.onlinestoreapp.data.mappers

import com.example.onlinestoreapp.data.model.Items
import com.example.onlinestoreapp.data.remote.model.ApiItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemsMapper @Inject constructor() {
    fun apiToModel(apiItems: ApiItems) = Items(
        items = apiItems.items
    )
}