package com.pdm0126.foodspoot.screens.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.pdm0126.foodspoot.screens.Favorites.FavoritesViewModel
import com.pdm0126.foodspoot.screens.cart.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToCart: () -> Unit,
    cartViewModel: CartViewModel,
    favoritesViewModel: FavoritesViewModel,
    viewModel: HomeViewModel = viewModel()
) {
    val groupedRestaurants by viewModel.groupedRestaurants.collectAsState()
    val totalItems by cartViewModel.totalItems.collectAsState(initial = 0)
    val favoriteIds by favoritesViewModel.favoriteIds.collectAsState()
    val selectedPriceRange by viewModel.selectedPriceRange.collectAsState()

    var showFilterSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    val favorites = groupedRestaurants.values.flatten()
        .filter { favoriteIds.contains(it.id) }
        .distinctBy { it.id }

    val activeFilters = if (selectedPriceRange != "Todos") 1 else 0

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("FoodSpot", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    BadgedBox(
                        badge = {
                            if (activeFilters > 0) {
                                Badge { Text("$activeFilters") }
                            }
                        }
                    ) {
                        IconButton(onClick = { showFilterSheet = true }) {
                            Icon(Icons.Default.Tune, contentDescription = "Filtros")
                        }
                    }
                },
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

        if (showFilterSheet) {
            ModalBottomSheet(
                onDismissRequest = { showFilterSheet = false },
                sheetState = sheetState
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 32.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Filtrar por precio",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        text = "Precio promedio del menú",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        viewModel.priceRanges.forEach { range ->
                            FilterChip(
                                selected = selectedPriceRange == range,
                                onClick = { viewModel.setPriceRange(range) },
                                label = { Text(range) },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    HorizontalDivider()

                    OutlinedButton(
                        onClick = { viewModel.setPriceRange("Todos") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Limpiar filtro")
                    }

                    Button(
                        onClick = { showFilterSheet = false },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Ver resultados")
                    }
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {

            if (favorites.isNotEmpty()) {
                item {
                    Text(
                        text = "Mis Favoritos",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        items(favorites) { restaurante ->
                            RestaurantCard(
                                restaurante = restaurante,
                                isFavorite = true,
                                onFavoriteClick = { favoritesViewModel.toggleFavorite(restaurante) },
                                onClick = { onNavigateToDetail(restaurante.id) }
                            )
                        }
                    }
                }
            }

            if (groupedRestaurants.isEmpty() || groupedRestaurants.values.all { it.isEmpty() }) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = "🍽️", style = MaterialTheme.typography.displayMedium)
                            Text(
                                text = "No hay restaurantes disponibles",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            groupedRestaurants.forEach { (categoria, listaDeRestaurantes) ->
                if (listaDeRestaurantes.isNotEmpty() || selectedPriceRange == "Todos") {
                    item {
                        Text(
                            text = categoria,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
                        )

                        if (listaDeRestaurantes.isEmpty()) {
                            Text(
                                text = "No hay opciones en esta categoría",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                                modifier = Modifier.padding(start = 24.dp, bottom = 16.dp)
                            )
                        } else {
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp)
                            ) {
                                items(listaDeRestaurantes) { restaurante ->
                                    RestaurantCard(
                                        restaurante = restaurante,
                                        isFavorite = favoriteIds.contains(restaurante.id),
                                        onFavoriteClick = { favoritesViewModel.toggleFavorite(restaurante) },
                                        onClick = { onNavigateToDetail(restaurante.id) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RestaurantCard(
    restaurante: Restaurant,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.width(220.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Box {
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
                IconButton(
                    onClick = onFavoriteClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavorite) "Quitar favorito" else "Agregar favorito",
                        tint = if (isFavorite) Color.Red else Color.White
                    )
                }
            }
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