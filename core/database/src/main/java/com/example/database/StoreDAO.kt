package com.example.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.database.model.InfoEntity
import com.example.database.model.ProductEntity
import com.example.database.model.ProductWithInfoEntity
import com.example.database.model.UserEntity
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
    suspend fun addAllInfo(info: List<InfoEntity>)

    @Transaction
    @Query("SELECT * FROM products")
    fun getProducts(): Flow<List<ProductWithInfoEntity>>
}