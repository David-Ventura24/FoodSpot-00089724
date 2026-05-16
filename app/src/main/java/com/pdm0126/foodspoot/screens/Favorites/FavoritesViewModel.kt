package com.pdm0126.foodspoot.screens.Favorites


import androidx.lifecycle.ViewModel
import com.pdm0126.foodspoot.model.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoritesViewModel : ViewModel() {

    private val _favoriteIds = MutableStateFlow<Set<Int>>(emptySet())
    val favoriteIds = _favoriteIds.asStateFlow()

    fun toggleFavorite(restaurant: Restaurant) {
        val current = _favoriteIds.value.toMutableSet()
        if (current.contains(restaurant.id)) {
            current.remove(restaurant.id)
        } else {
            current.add(restaurant.id)
        }
        _favoriteIds.value = current
    }

    fun isFavorite(restaurantId: Int): Boolean {
        return _favoriteIds.value.contains(restaurantId)
    }
}