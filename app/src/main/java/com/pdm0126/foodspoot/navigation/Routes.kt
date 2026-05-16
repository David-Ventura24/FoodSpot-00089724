package com.pdm0126.foodspoot.navigation


import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes() : NavKey {

    @Serializable
    data object Home : Routes()

    @Serializable
    data object Search : Routes()

    @Serializable
    data class Detail(val restaurantId: Int) : Routes()
    @Serializable
    data object Cart: Routes()


    @Serializable
    data object OrderSuccess : Routes()
}

