package com.example.onlinestoreapp.data.remote.repository

import com.example.onlinestoreapp.data.mappers.ItemsMapper
import com.example.onlinestoreapp.data.model.Items
import com.example.onlinestoreapp.data.remote.StoreApiService
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeApiService: StoreApiService,
    private val itemsMapper: ItemsMapper
) : StoreRepository  {
    override suspend fun getCatalog(): Items {


        return itemsMapper.apiToModel(storeApiService.getCatalog())
    }
}