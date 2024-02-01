package com.example.onlinestoreapp.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ApiPrice(
    @SerialName("price")
    val price: String,
    @SerialName("discount")
    val discount: Int,
    @SerialName("priceWithDiscount")
    val priceWithDiscount: String,
    @SerialName("unit")
    val unit: String
)