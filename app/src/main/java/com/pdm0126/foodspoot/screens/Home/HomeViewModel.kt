package com.pdm0126.foodspoot.screens.Home

import androidx.lifecycle.ViewModel
import com.pdm0126.foodspoot.data.RestaurantRepository
import com.pdm0126.foodspoot.data.RestaurantRepositoryImpl
import com.pdm0126.foodspoot.model.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val repository: RestaurantRepository = RestaurantRepositoryImpl()

    private val _groupedRestaurants = MutableStateFlow<Map<String, List<Restaurant>>>(emptyMap())
    val groupedRestaurants: StateFlow<Map<String, List<Restaurant>>> = _groupedRestaurants.asStateFlow()

    init {
        fetchAndGroupRestaurants()
    }

    private fun fetchAndGroupRestaurants() {
        val allRestaurants = repository.getRestaurants()


        val grouped = allRestaurants.groupBy { it.categories.firstOrNull() ?: "General" }
       
        _groupedRestaurants.update { grouped }
    }
}
