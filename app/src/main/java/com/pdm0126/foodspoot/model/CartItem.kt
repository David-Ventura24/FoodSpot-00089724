package com.pdm0126.foodspoot.model

data class CartItem(
    val dish: Dish,
    var quantity: Int = 1
)