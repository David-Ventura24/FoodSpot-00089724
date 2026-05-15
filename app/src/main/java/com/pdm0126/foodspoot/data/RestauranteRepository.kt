package com.pdm0126.foodspoot.data

import com.pdm0126.foodspoot.model.Platillo
import com.pdm0126.foodspoot.model.Restaurante

interface RestauranteRepository {
    fun getRestaurants(): List<Restaurante>
}

class RestauranteRepositoryImpl : RestauranteRepository {

    override fun getRestaurants(): List<Restaurante> {
        return listOf(

            Restaurante(
                id = 1, name = "Pollo Campero", description = "Tierno, jugoso y crujiente",
                imageUrl = "https://images.unsplash.com/photo-1626645738196-c2a7c8d08f58",
                categories = listOf("Rápida", "Pollo"),
                menu = listOf(Platillo(1, "Súper Campero", "3 piezas y guarniciones", "https://images.unsplash.com/photo-1562967914-608f82629710"))
            ),
            Restaurante(
                id = 2, name = "Pollo Campestre", description = "Como a ti te gusta",
                imageUrl = "https://images.unsplash.com/photo-1626074353765-517a681e40be",
                categories = listOf("Rápida", "Pollo"),
                menu = listOf(Platillo(2, "Menú Campestre", "Pollo frito tradicional", "https://images.unsplash.com/photo-1610057099431-d73a1c9d2f2f"))
            ),
            Restaurante(
                id = 3, name = "Donkey", description = "Burritos y tacos gigantes",
                imageUrl = "https://images.unsplash.com/photo-1584031036380-3fb6f2d51880",
                categories = listOf("Mexicana", "Burritos"),
                menu = listOf(Platillo(3, "Burrito Donkey", "Carne, frijoles y queso", "https://images.unsplash.com/photo-1626700051175-6818013e1d4f"))
            ),

            Restaurante(
                id = 4, name = "Panda Express", description = "Comida china-americana",
                imageUrl = "https://images.unsplash.com/photo-1525755662778-989d0524087e",
                categories = listOf("Asiática", "Rápida"),
                menu = listOf(Platillo(4, "Orange Chicken", "Pollo agridulce icónico", "https://images.unsplash.com/photo-1585032295866-aa530d17d099"))
            ),
            Restaurante(
                id = 5, name = "China Wok", description = "Sabor oriental al instante",
                imageUrl = "https://images.unsplash.com/photo-1512058560366-cd24270083cd",
                categories = listOf("Asiática", "Wok"),
                menu = listOf(Platillo(5, "Arroz Chaufa", "Arroz frito con carnes", "https://images.unsplash.com/photo-1512058454905-6b841e7ad132"))
            ),
            Restaurante(
                id = 6, name = "Sushi Itto", description = "Sushi con toque local",
                imageUrl = "https://images.unsplash.com/photo-1579871494447-9811cf80d66c",
                categories = listOf("Asiática", "Sushi"),
                menu = listOf(Platillo(6, "Emperador Roll", "Cangrejo y queso crema", "https://images.unsplash.com/photo-1553621042-f6e147245754"))
            ),

            Restaurante(
                id = 7, name = "Típicos Margoth", description = "Tradición salvadoreña desde 1962",
                imageUrl = "https://images.unsplash.com/photo-1624601256323-3c7473abc157",
                categories = listOf("Típicos", "Desayunos"),
                menu = listOf(Platillo(7, "Pupusas", "Revueltas, queso o chicharrón", "https://images.unsplash.com/photo-1634641940900-5883b1d3d623"))
            ),
            Restaurante(
                id = 8, name = "Pupusería El Sopón", description = "Pupusas y caldos",
                imageUrl = "https://images.unsplash.com/photo-1628191137573-dee64e727614",
                categories = listOf("Típicos", "Pupusas"),
                menu = listOf(Platillo(8, "Pupusa de Ayote", "Con extra queso", "https://images.unsplash.com/photo-1619860860774-1e2e17343432"))
            ),

            Restaurante(
                id = 9, name = "Pizza Hut", description = "Haciendo que cada momento cuente",
                imageUrl = "https://images.unsplash.com/photo-1513104890138-7c749659a591",
                categories = listOf("Italiana", "Pizzas"),
                menu = listOf(Platillo(9, "Pizza de Jamón", "Masa original", "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38"))
            ),
            Restaurante(
                id = 10, name = "Toscana", description = "Pastas artesanales",
                imageUrl = "https://images.unsplash.com/photo-1473093226795-af9932fe5856",
                categories = listOf("Italiana", "Pastas"),
                menu = listOf(Platillo(10, "Lasagna Forno", "Carne y bechamel", "https://images.unsplash.com/photo-1619895092538-128341789043"))
            ),

            Restaurante(
                id = 11, name = "La Pampa", description = "Cortes premium",
                imageUrl = "https://images.unsplash.com/photo-1544025162-d76694265947",
                categories = listOf("Carnes", "Parrilla"),
                menu = listOf(Platillo(11, "Puyazo", "Corte nacional tierno", "https://images.unsplash.com/photo-1546241072-48010ad28c2c"))
            ),
            Restaurante(
                id = 12, name = "Tony Roma's", description = "Famosos por sus costillas",
                imageUrl = "https://images.unsplash.com/photo-1544077960-604201fe74bc",
                categories = listOf("Carnes", "Costillas"),
                menu = listOf(Platillo(12, "Baby Back Ribs", "Salsa BBQ original", "https://images.unsplash.com/photo-1529193591184-b1d58069ecdd"))
            ),
            Restaurante(
                id = 13, name = "Go Green", description = "Ensaladas frescas",
                imageUrl = "https://images.unsplash.com/photo-1512621776951-a57141f2eefd",
                categories = listOf("Saludable", "Ensaladas"),
                menu = listOf(Platillo(13, "Ensalada César", "Pollo grillé", "https://images.unsplash.com/photo-1550304943-4f24f54ddde9"))
            ),
            Restaurante(
                id = 14, name = "Subway", description = "Come fresco",
                imageUrl = "https://images.unsplash.com/photo-1534352591606-11b201a6455e",
                categories = listOf("Saludable", "Sandwiches"),
                menu = listOf(Platillo(14, "Sub de Pavo", "Pan de 15cm", "https://images.unsplash.com/photo-1592415499556-74fcb9f18667"))
            ),
            Restaurante(
                id = 15, name = "Buffalo Wings", description = "Expertos en alitas",
                imageUrl = "https://images.unsplash.com/photo-1567620832903-9fc6debc209f",
                categories = listOf("Rápida", "Alitas"),
                menu = listOf(Platillo(15, "10 Alitas", "Salsa Atomic", "https://images.unsplash.com/photo-1527477396000-e27163b481c2"))
            ),
            Restaurante(
                id = 16, name = "Koi Sushi", description = "Fusión japonesa",
                imageUrl = "https://images.unsplash.com/photo-1579871494447-9811cf80d66c",
                categories = listOf("Asiática", "Sushi"),
                menu = listOf(Platillo(16, "California Roll", "Clásico de la casa", "https://images.unsplash.com/photo-1553621042-f6e147245754"))
            )
        )
    }
}
