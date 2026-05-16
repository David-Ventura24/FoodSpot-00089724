package com.pdm0126.foodspoot.screens.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.pdm0126.foodspoot.model.Restaurant
import com.pdm0126.foodspoot.screens.cart.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToCart: () -> Unit,
    cartViewModel: CartViewModel,
    viewModel: HomeViewModel = viewModel()
) {
    val groupedRestaurants by viewModel.groupedRestaurants.collectAsState()
    val totalItems by cartViewModel.totalItems.collectAsState(initial = 0)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("FoodSpot", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = onNavigateToSearch) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                    BadgedBox(
                        badge = {
                            if (totalItems > 0) {
                                Badge { Text("$totalItems") }
                            }
                        }
                    ) {
                        IconButton(onClick = onNavigateToCart) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
                        }
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            groupedRestaurants.forEach { (categoria, listaDeRestaurantes) ->
                item {
                    Text(
                        text = categoria,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        items(listaDeRestaurantes) { restaurante ->
                            RestaurantCard(
                                restaurante = restaurante,
                                onClick = { onNavigateToDetail(restaurante.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RestaurantCard(restaurante: Restaurant, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.width(220.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            SubcomposeAsyncImage(
                model = restaurante.imageUrl,
                contentDescription = "Imagen de ${restaurante.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
                loading = {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Cargando imagen...",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.DarkGray
                        )
                    }
                },
                error = {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFE0E0E0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Error al cargar",
                            tint = Color.Gray
                        )
                    }
                }
            )

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = restaurante.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Text(
                    text = restaurante.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
