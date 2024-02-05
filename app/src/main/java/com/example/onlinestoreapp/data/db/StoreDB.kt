package com.example.onlinestoreapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.onlinestoreapp.data.db.model.InfoEntity
import com.example.onlinestoreapp.data.db.model.ProductEntity
import com.example.onlinestoreapp.data.db.model.UserEntity

@Database(entities = [ProductEntity::class, UserEntity::class, InfoEntity::class], version = 1)
abstract class StoreDB : RoomDatabase() {
    abstract fun storeDAO(): StoreDAO
}