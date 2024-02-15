package com.example.network.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiFeedback(
    @SerialName("count")
    val count: Int,
    @SerialName("rating")
    val rating: Float,
)