package com.example.onlinestoreapp.data.db.repository

import com.example.onlinestoreapp.data.db.StoreDAO
import com.example.onlinestoreapp.data.db.model.UserEntity
import com.example.onlinestoreapp.data.mappers.ProductDBMapper
import com.example.onlinestoreapp.data.mappers.UserMapper
import com.example.onlinestoreapp.data.model.User
import com.example.onlinestoreapp.data.remote.model.ApiProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreDBRepositoryImpl @Inject constructor(
    private val storeDAO: StoreDAO,
    private val productDBMapper: ProductDBMapper,
    private val userMapper: UserMapper
) : StoreDBRepository {
    override suspend fun addProduct(product: ApiProduct) {
        storeDAO.addProduct(productDBMapper.fromUIModelToEntity(product))
        storeDAO.addAllInfo(productDBMapper.fromInfoUIModelToEntity(product))
    }

    override fun getUser(): Flow<User?> {
        return storeDAO.getUser().map { list ->
            if (list.isNotEmpty()) {
                userMapper.fromEntityToUIModel(list.first())
            } else
                null
        }
    }

    override suspend fun addUser(name: String, surname: String, phone: String) {
        storeDAO.addUser(UserEntity(name = name, surname = surname, phone = phone))
    }

    override fun getProducts(): Flow<List<ApiProduct>> {
        return storeDAO.getProducts().map { list ->
            list.map {
                productDBMapper.fromEntityToUIModel(it)
            }
        }
    }

    override suspend fun deleteProduct(product: ApiProduct) {
        storeDAO.deleteProduct(productDBMapper.fromUIModelToEntity(product))
    }

    override suspend fun likeProduct(product: ApiProduct) {
        try {
            addProduct(product)
        } catch (_: Throwable) {
            deleteProduct(product)
        }
    }
}