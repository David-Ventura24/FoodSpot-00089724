package com.pdm0126.foodspoot.model


data class Dish(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: Double
)