package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.model.InfoEntity
import com.example.database.model.ProductEntity
import com.example.database.model.UserEntity

@Database(entities = [ProductEntity::class, UserEntity::class, InfoEntity::class], version = 1)
abstract class StoreDB : RoomDatabase() {
    abstract fun storeDAO(): StoreDAO
}