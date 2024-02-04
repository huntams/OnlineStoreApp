package com.example.onlinestoreapp.data.model

import com.example.onlinestoreapp.data.remote.model.ApiFeedback
import com.example.onlinestoreapp.data.remote.model.ApiInfo
import com.example.onlinestoreapp.data.remote.model.ApiPrice


data class Product(
    val id : String,
    val title: String,
    val subtitle: String,
    val price: ApiPrice,
    val feedback: ApiFeedback,
    val tags: List<String>,
    val available: Int,
    val description: String,
    val info : List<ApiInfo>,
    val ingredients: String,
    var like: Boolean=true,
)