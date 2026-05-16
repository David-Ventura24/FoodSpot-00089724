package com.pdm0126.foodspoot.screens.Home

import androidx.lifecycle.ViewModel
import com.pdm0126.foodspoot.data.RestaurantRepository
import com.pdm0126.foodspoot.data.RestaurantRepositoryImpl
import com.pdm0126.foodspoot.model.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val repository: RestaurantRepository = RestaurantRepositoryImpl()
    private val allRestaurants = repository.getRestaurants()

    val officialCategories = listOf("Rápida", "Típicos", "Asiática", "Saludable", "Carnes", "Italiana")

    val priceRanges = listOf("Todos", "Menos de $5", "$5 - $10", "Más de $10")

    private val _selectedCategory = MutableStateFlow("Todas")
    val selectedCategory = _selectedCategory.asStateFlow()

    private val _selectedPriceRange = MutableStateFlow("Todos")
    val selectedPriceRange = _selectedPriceRange.asStateFlow()

    private val _groupedRestaurants = MutableStateFlow<Map<String, List<Restaurant>>>(emptyMap())
    val groupedRestaurants: StateFlow<Map<String, List<Restaurant>>> = _groupedRestaurants.asStateFlow()

    init {
        applyFilters()
    }

    fun setCategory(category: String) {
        _selectedCategory.value = category
        applyFilters()
    }

    fun setPriceRange(range: String) {
        _selectedPriceRange.value = range
        applyFilters()
    }

    private fun applyFilters() {
        val category = _selectedCategory.value
        val priceRange = _selectedPriceRange.value

        val filtered = allRestaurants.filter { restaurant ->
            val categoryMatch = category == "Todas" || restaurant.categories.contains(category)

            val avgPrice = restaurant.menu.map { it.price }.average()
            val priceMatch = when (priceRange) {
                "Menos de $5" -> avgPrice < 5.0
                "$5 - $10" -> avgPrice in 5.0..10.0
                "Más de $10" -> avgPrice > 10.0
                else -> true
            }

            categoryMatch && priceMatch
        }

        if (category == "Todas") {
            val finalMap = linkedMapOf<String, List<Restaurant>>()
            officialCategories.forEach { cat ->
                finalMap[cat] = emptyList()
            }

            filtered.forEach { restaurant ->
                restaurant.categories.forEach { cat ->
                    if (finalMap.containsKey(cat)) {
                        finalMap[cat] = finalMap[cat].orEmpty() + restaurant
                    }
                }
            }


            _groupedRestaurants.value = if (priceRange == "Todos") {
                finalMap
            } else {
                finalMap.filterValues { it.isNotEmpty() }
            }
        } else {
            _groupedRestaurants.value = mapOf(category to filtered)
        }
    }
}