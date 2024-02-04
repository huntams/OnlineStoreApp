package com.example.onlinestoreapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.onlinestoreapp.data.db.model.InfoEntity
import com.example.onlinestoreapp.data.db.model.ProductEntity
import com.example.onlinestoreapp.data.db.model.ProductWithInfoEntity
import com.example.onlinestoreapp.data.db.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDAO {


    @Delete
    suspend fun deleteProduct(product: ProductEntity)
    @Query("SELECT * FROM users")
    fun getUser(): Flow<List<UserEntity>>
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addProduct(product: ProductEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllInfo(info:List<InfoEntity>)
    @Transaction
    @Query("SELECT * FROM products")
    fun getProducts(): Flow<List<ProductWithInfoEntity>>
}