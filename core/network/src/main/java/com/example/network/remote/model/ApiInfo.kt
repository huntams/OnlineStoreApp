package com.example.network.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiInfo(
    @SerialName("title")
    val title: String,
    @SerialName("value")
    val value: String,
)