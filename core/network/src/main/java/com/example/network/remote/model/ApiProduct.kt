package com.example.network.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiProduct(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("subtitle")
    val subtitle: String,
    @SerialName("price")
    val price: ApiPrice,
    @SerialName("feedback")
    val feedback: ApiFeedback,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("available")
    val available: Int,
    @SerialName("description")
    val description: String,
    @SerialName("info")
    val info: List<ApiInfo>,
    @SerialName("ingredients")
    val ingredients: String,
    var like: Boolean = false,
)