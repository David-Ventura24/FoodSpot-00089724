package com.pdm0126.foodspoot.model

data class Restaurante(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val categories: List<String>,
    val menu: List<Platillo>
)