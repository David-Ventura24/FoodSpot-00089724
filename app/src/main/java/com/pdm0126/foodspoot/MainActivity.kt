package com.pdm0126.foodspoot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm0126.foodspoot.navigation.MainNavigation
import com.pdm0126.foodspoot.screens.cart.CartViewModel
import com.pdm0126.foodspoot.ui.theme.FoodSpootTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodSpootTheme {
                val cartViewModel: CartViewModel = viewModel()
                MainNavigation(cartViewModel = cartViewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodSpootTheme {
        Greeting("Android")
    }
}
