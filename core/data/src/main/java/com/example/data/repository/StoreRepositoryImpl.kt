package com.example.data.repository

import com.example.data.mappers.ItemsMapper
import com.example.model.Items
import com.example.network.remote.StoreApiService
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeApiService: StoreApiService,
    private val itemsMapper: ItemsMapper
) : StoreRepository {
    override suspend fun getCatalog(): Items {


        return itemsMapper.apiToModel(storeApiService.getCatalog())
    }
}