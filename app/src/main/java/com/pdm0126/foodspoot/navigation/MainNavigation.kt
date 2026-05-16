package com.pdm0126.foodspoot.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm0126.foodspoot.screens.cart.CartScreen
import com.pdm0126.foodspoot.screens.cart.CartViewModel
import com.pdm0126.foodspoot.screens.Home.HomeScreen
import com.pdm0126.foodspoot.screens.Detail.DetailScreen
import com.pdm0126.foodspoot.screens.Search.SearchScreen
import com.pdm0126.foodspoot.screens.cart.OrderSuccessScreen

@Composable
fun MainNavigation(cartViewModel: CartViewModel) {
    val backStack = rememberNavBackStack(Routes.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {

            entry<Routes.Home> {
                HomeScreen(
                    cartViewModel = cartViewModel,
                    onNavigateToSearch = { backStack.add(Routes.Search) },
                    onNavigateToDetail = { restaurantId ->
                        backStack.add(Routes.Detail(restaurantId))
                    },
                    onNavigateToCart = { backStack.add(Routes.Cart) }
                )
            }

            entry<Routes.Search> {
                SearchScreen(
                    navigateToDetail = { restaurantId ->
                        backStack.add(Routes.Detail(restaurantId))
                    },
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<Routes.Detail> { route ->
                DetailScreen(
                    restaurantId = route.restaurantId,
                    cartViewModel = cartViewModel,
                    onBack = { backStack.removeLastOrNull() },
                    onNavigateToCart = { backStack.add(Routes.Cart) }
                )
            }

            entry<Routes.Cart> {
                CartScreen(
                    viewModel = cartViewModel,
                    onBack = { backStack.removeLastOrNull() },
                    navigateToSuccessScreen = { backStack.add(Routes.OrderSuccess) }
                )
            }

            entry<Routes.OrderSuccess> {
                val items by cartViewModel.items.collectAsState()
                val subtotal by cartViewModel.subtotal.collectAsState()
                val paymentMethod by cartViewModel.selectedPaymentMethod.collectAsState()

                OrderSuccessScreen(
                    items = items,
                    subtotal = subtotal,
                    paymentMethod = paymentMethod,
                    onBackToHome = {
                        cartViewModel.clearCart()
                        backStack.removeIf { true }
                        backStack.add(Routes.Home)
                    }
                )
            }
        }
    )
}