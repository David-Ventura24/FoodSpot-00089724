package com.pdm0126.foodspoot.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm0126.foodspoot.screens.Home.HomeScreen
import com.pdm0126.foodspoot.screens.Detail.DetailScreen
import com.pdm0126.foodspoot.screens.Search.SearchScreen


@Composable
fun MainNavigation() {
    val backStack = rememberNavBackStack(Routes.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {

            // 1. Pantalla de Inicio (Lista)
            entry<Routes.Home> {
                HomeScreen(
                    navigateToSearch = {
                        backStack.add(Routes.Search)
                    },
                    navigateToDetail = { restaurantId ->
                        backStack.add(Routes.Detail(restaurantId))
                    }
                )
            }

            // 2. Pantalla de Búsqueda
            entry<Routes.Search> {
                SearchScreen(
                    navigateToDetail = { restaurantId ->
                        backStack.add(Routes.Detail(restaurantId))
                    },
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            // 3. Pantalla de Detalle
            entry<Routes.Detail> { route ->
                DetailScreen(
                    restaurantId = route.restaurantId,
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        }
    )
}