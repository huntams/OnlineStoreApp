package com.example.data.repository

import com.example.data.mappers.ProductDBMapper
import com.example.data.mappers.UserMapper
import com.example.database.StoreDAO
import com.example.database.model.UserEntity
import com.example.model.Product
import com.example.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreDBRepositoryImpl @Inject constructor(
    private val storeDAO: StoreDAO,
    private val productDBMapper: ProductDBMapper,
    private val userMapper: UserMapper
) : StoreDBRepository {
    override suspend fun addProduct(product: Product) {
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

    override fun getProducts(): Flow<List<Product>> {
        return storeDAO.getProducts().map { list ->
            list.map {
                productDBMapper.fromEntityToUIModel(it)
            }
        }
    }

    override suspend fun deleteProduct(product: Product) {
        storeDAO.deleteProduct(productDBMapper.fromUIModelToEntity(product))
    }

    override suspend fun likeProduct(product: Product) {
        try {
            addProduct(product)
        } catch (_: Throwable) {
            deleteProduct(product)
        }
    }
}