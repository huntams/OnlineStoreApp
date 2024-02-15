package com.example.model

import androidx.annotation.DrawableRes

data class Image(
    val id: String,
    @DrawableRes val image: Int,
)