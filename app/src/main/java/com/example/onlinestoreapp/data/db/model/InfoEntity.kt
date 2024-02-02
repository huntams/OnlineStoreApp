package com.example.onlinestoreapp.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "info")
data class InfoEntity(
    @PrimaryKey(autoGenerate = true) val infoId: Long = 0,
    @ColumnInfo(name = "product_creator_id")
    val productId: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "value")
    val value: String
)


