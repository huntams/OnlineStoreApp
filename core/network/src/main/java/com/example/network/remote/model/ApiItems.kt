package com.example.network.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiItems(
    @SerialName("items")
    val items: List<ApiProduct>
)