package com.example.onlinestoreapp.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @ColumnInfo(name = "user_id")
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name = "surname")
    val surname: String,
    @ColumnInfo(name = "phone")
    val phone: Long,
)