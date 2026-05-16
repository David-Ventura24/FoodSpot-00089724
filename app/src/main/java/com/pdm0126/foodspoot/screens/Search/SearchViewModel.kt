package com.pdm0126.foodspoot.screens.Search

import androidx.lifecycle.ViewModel
import com.pdm0126.foodspoot.data.RestaurantRepository
import com.pdm0126.foodspoot.data.RestaurantRepositoryImpl
import com.pdm0126.foodspoot.model.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel : ViewModel() {

    private val repository: RestaurantRepository = RestaurantRepositoryImpl()

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _results = MutableStateFlow<List<Restaurant>>(emptyList())
    val results = _results.asStateFlow()

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
        _results.value = repository.searchRestaurants(newQuery)
    }
}