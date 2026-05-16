package com.pdm0126.foodspoot.screens.Detail

import androidx.lifecycle.ViewModel
import com.pdm0126.foodspoot.data.RestaurantRepository
import com.pdm0126.foodspoot.data.RestaurantRepositoryImpl
import com.pdm0126.foodspoot.model.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel : ViewModel() {

    private val repository: RestaurantRepository = RestaurantRepositoryImpl()

    private val _restaurant = MutableStateFlow<Restaurant?>(null)
    val restaurant = _restaurant.asStateFlow()

    fun loadRestaurant(id: Int) {
        _restaurant.value = repository.getRestaurantById(id)
    }
}