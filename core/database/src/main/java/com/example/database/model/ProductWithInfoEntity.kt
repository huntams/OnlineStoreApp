package com.example.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class ProductWithInfoEntity(
    @Embedded val productEntity: ProductEntity,
    @Relation(
        parentColumn = "product_id",
        entityColumn = "product_creator_id"
    )
    val info: List<InfoEntity>
)