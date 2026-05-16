package com.pdm0126.foodspoot.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm0126.foodspoot.model.CartItem
import com.pdm0126.foodspoot.model.Dish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CartViewModel : ViewModel() {

    private val _items = MutableStateFlow<List<CartItem>>(emptyList())
    val items = _items.asStateFlow()

    val totalItems = _items.map { list ->
        list.sumOf { it.quantity }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)

    val subtotal = _items.map { list ->
        list.sumOf { it.dish.price * it.quantity }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0.0)

    private val _selectedPaymentMethod = MutableStateFlow("Efectivo")
    val selectedPaymentMethod = _selectedPaymentMethod.asStateFlow()

    fun setPaymentMethod(method: String) {
        _selectedPaymentMethod.value = method
    }

    fun addItem(dish: Dish) {
        val current = _items.value
        val existing = current.find { it.dish.id == dish.id }
        if (existing != null) {
            _items.value = current.map {
                if (it.dish.id == dish.id) it.copy(quantity = it.quantity + 1)
                else it
            }
        } else {
            _items.value = current + CartItem(dish)
        }
    }

    fun removeItem(dish: Dish) {
        val current = _items.value
        val existing = current.find { it.dish.id == dish.id }
        if (existing != null) {
            if (existing.quantity > 1) {
                _items.value = current.map {
                    if (it.dish.id == dish.id) it.copy(quantity = it.quantity - 1)
                    else it
                }
            } else {
                _items.value = current.filter { it.dish.id != dish.id }
            }
        }
    }

    fun clearCart() {
        _items.value = emptyList()
    }
}