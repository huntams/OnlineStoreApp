package com.example.model


data class Product(
    val id : String,
    val title: String,
    val subtitle: String,
    val price: Price,
    val feedback: Feedback,
    val tags: List<String>,
    val available: Int,
    val description: String,
    val info : List<Info>,
    val ingredients: String,
    val images: List<Image>,
    var like: Boolean=true,
)