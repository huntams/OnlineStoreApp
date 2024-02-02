package com.example.onlinestoreapp.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.onlinestoreapp.data.db.TagsConverter

@Entity(tableName = "products")
@TypeConverters(TagsConverter::class)
data class ProductEntity(
    @ColumnInfo(name = "product_id")
    @PrimaryKey val id:String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "subtitle")
    val subtitle: String,
    @ColumnInfo(name = "price")
    val price: String,
    @ColumnInfo("discount")
    val discount: Int,
    @ColumnInfo("price_with_discount")
    val priceWithDiscount: String,
    @ColumnInfo("unit")
    val unit: String,
    @ColumnInfo(name = "count")
    val count: Int,
    @ColumnInfo(name = "rating")
    val rating: Float,
    @ColumnInfo(name = "tags")
    val tags: List<String>,
    @ColumnInfo(name = "available")
    val available: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "ingredients")
    val ingredients: String,
)