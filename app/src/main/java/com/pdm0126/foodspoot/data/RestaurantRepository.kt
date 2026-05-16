package com.pdm0126.foodspoot.data

import com.pdm0126.foodspoot.model.Dish
import com.pdm0126.foodspoot.model.Restaurant
import java.text.Normalizer

interface RestaurantRepository {
    fun getRestaurants(): List<Restaurant>
    fun getRestaurantById(id: Int): Restaurant?
    fun searchRestaurants(query: String): List<Restaurant>
}

class RestaurantRepositoryImpl : RestaurantRepository {

    private fun String.normalize(): String {
        return Normalizer.normalize(this, Normalizer.Form.NFD)
            .replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
            .lowercase()
            .trim()
    }

    private val restaurants = listOf(
        Restaurant(
            id = 1, name = "Pollo Campero",
            description = "Tierno, jugoso y crujiente",
            imageUrl = "https://images.unsplash.com/photo-1562967914-608f82629710?w=500",
            categories = listOf("Rápida", "Pollo"),
            menu = listOf(
                Dish(1, "Súper Campero", "3 piezas con guarniciones", "https://images.unsplash.com/photo-1562967914-608f82629710?w=400", 5.99),
                Dish(2, "Menú Familiar", "8 piezas para compartir", "https://images.unsplash.com/photo-1626074353765-517a681e40be?w=400", 12.99),
                Dish(3, "Combo Personal", "2 piezas con papas y refresco", "https://i.imgur.com/7JTaE1m.jpeg", 4.50)
            )
        ),
        Restaurant(
            id = 2, name = "Típicos Margoth",
            description = "Tradición salvadoreña desde 1962",
            imageUrl = "https://i.imgur.com/jZBOBwE.jpeg",
            categories = listOf("Típicos", "Desayunos"),
            menu = listOf(
                Dish(4, "Pupusas Revueltas", "Chicharrón y queso", "https://i.imgur.com/IKpGaz0.png", 1.50),
                Dish(5, "Desayuno", "Huevos, tocino y pan", "https://media.istockphoto.com/id/2172549705/photo/breakfast-meal-plate-with-fried-eggs-bacon-toast-and-sausages-viewed-close-up.jpg?s=1024x1024&w=is&k=20&c=N1gJkyWYv4lDcYY-ozmAiAu0p8Nh16aauj0_3mxEnVg=", 3.50),
                Dish(6, "Sopa de Pata", "Tradicional receta salvadoreña", "https://images.unsplash.com/photo-1603105037880-880cd4edfb0d?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 4.00)
            )
        ),
        Restaurant(
            id = 3, name = "Pupusería El Sopón",
            description = "Pupusas y caldos artesanales",
            imageUrl = "https://i.imgur.com/IKpGaz0.png",
            categories = listOf("Típicos", "Pupusas"),
            menu = listOf(
                Dish(7, "Pupusa de Queso", "Queso derretido artesanal", "https://media.istockphoto.com/id/1410357879/photo/salvadorian-pupusas.jpg?s=1024x1024&w=is&k=20&c=vFKDPJSMPSeEuikIxOmyyo3uRBI8KAOqBC85HJ3hG2I=", 1.25),
                Dish(8, "Pupusa de Ayote", "Con extra queso", "https://media.istockphoto.com/id/1456062975/photo/food-photos-various-entrees-appetizers-deserts-etc.jpg?s=1024x1024&w=is&k=20&c=4uLz_tVbRoJd1kcRf6GvwVyNQBuMAUNIhWlXOriu5zg=", 1.25),
                Dish(9, "Caldo de Res", "Con verduras frescas", "https://media.istockphoto.com/id/2220961564/photo/a-hearty-soup-brimming-with-fresh-vegetables-and-tender-rice-embodying-nourishment-and.jpg?s=1024x1024&w=is&k=20&c=KUrWBJpDajuXXhRrggBDpAd58b8foVXV4IQkFU3sTxg=", 4.50)
            )
        ),
        Restaurant(
            id = 4, name = "Sushi Itto",
            description = "Sushi con toque local",
            imageUrl = "https://images.unsplash.com/photo-1579871494447-9811cf80d66c?w=500",
            categories = listOf("Asiática", "Sushi"),
            menu = listOf(
                Dish(10, "Emperador Roll", "Cangrejo y queso crema", "https://images.unsplash.com/photo-1553621042-f6e147245754?w=400", 8.99),
                Dish(11, "California Roll", "Clásico con aguacate", "https://media.istockphoto.com/id/2152180681/photo/set-of-sushi-rolls-in-a-cafe.jpg?s=1024x1024&w=is&k=20&c=lGSoYN0Nsa87CRErfUnCYX5r7c-IlOL5BzyLM0L51Gc=", 7.50),
                Dish(12, "Spicy Tuna Roll", "Atún picante con sriracha", "https://images.unsplash.com/photo-1559410545-0bdcd187e0a6?w=400", 9.25)
            )
        ),
        Restaurant(
            id = 5, name = "China Wok",
            description = "Sabor oriental al instante",
            imageUrl = "https://media.istockphoto.com/id/545286388/photo/chinese-food-blank-background.jpg?s=1024x1024&w=is&k=20&c=KxgiUigzYxtMqahfVpGMM6SHfLOHCZb4Mby0At2UbFQ=",
            categories = listOf("Asiática", "Wok"),
            menu = listOf(
                Dish(13, "Arroz Chaufa", "Arroz frito con carnes", "https://media.istockphoto.com/id/1465603466/photo/peruvian-traditional-food-rice-with-meat-and-vegetable-scramble-eggs-decorated-with-onian.jpg?s=1024x1024&w=is&k=20&c=ElfmHOqKzSgHU_6yPQqkrAHIYUj-p4VDqM-GlxRGOvU=", 5.50),
                Dish(14, "Chop Suey", "Vegetales salteados con pollo", "https://media.istockphoto.com/id/1324972504/photo/teriyaki.jpg?s=1024x1024&w=is&k=20&c=6uB3ghDCuWDBtQ0r7-FCCdeHMmCXcz9PKNb4U17MIbs=", 6.00),
                Dish(15, "Wonton Frito", "6 unidades con salsa agridulce", "https://images.unsplash.com/photo-1525755662778-989d0524087e?w=400", 3.75)
            )
        ),
        Restaurant(
            id = 6, name = "Pizza Hut",
            description = "Haciendo que cada momento cuente",
            imageUrl = "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=500",
            categories = listOf("Italiana", "Pizzas"),
            menu = listOf(
                Dish(16, "Pizza de Jamón", "Masa original con jamón", "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=400", 9.99),
                Dish(17, "Pizza Pepperoni", "Doble pepperoni", "https://images.unsplash.com/photo-1534308983496-4fabb1a015ee?w=400", 10.99),
                Dish(18, "Pizza Hawaiana", "Jamón y piña", "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=400", 9.50)
            )
        ),
        Restaurant(
            id = 7, name = "Toscana",
            description = "Pastas artesanales al estilo italiano",
            imageUrl = "https://images.unsplash.com/photo-1473093226795-af9932fe5856?w=500",
            categories = listOf("Italiana", "Pastas"),
            menu = listOf(
                Dish(19, "Lasagna Forno", "Carne molida y bechamel", "https://images.unsplash.com/photo-1619895092538-128341789043?w=400", 11.50),
                Dish(20, "Fettuccine Alfredo", "Crema y parmesano", "https://images.unsplash.com/photo-1555949258-eb67b1ef0ceb?w=400", 10.25),
                Dish(21, "Risotto de Hongos", "Arroz cremoso con portobello", "https://images.unsplash.com/photo-1476124369491-e7addf5db371?w=400", 12.00)
            )
        ),
        Restaurant(
            id = 8, name = "La Pampa",
            description = "Cortes premium a la parrilla",
            imageUrl = "https://images.unsplash.com/photo-1544025162-d76694265947?w=500",
            categories = listOf("Carnes", "Parrilla"),
            menu = listOf(
                Dish(22, "Puyazo", "Corte nacional tierno al carbón", "https://media.istockphoto.com/id/1461353896/photo/beef-steak-with-fries.jpg?s=1024x1024&w=is&k=20&c=VeXpPqS-ufs-KV2r8YDx-KHDuFC1rXttMjyME1AV_jo=", 14.99),
                Dish(23, "T-Bone", "450g término medio", "https://images.unsplash.com/photo-1529193591184-b1d58069ecdd?w=400", 18.50),
                Dish(24, "Costillas BBQ", "Con salsa de la casa", "https://images.unsplash.com/photo-1544025162-d76694265947?w=400", 16.00)
            )
        ),
        Restaurant(
            id = 9, name = "Subway",
            description = "Come fresco todos los días",
            imageUrl = "https://images.unsplash.com/photo-1509722747041-616f39b57569?w=500",
            categories = listOf("Saludable", "Sandwiches"),
            menu = listOf(
                Dish(25, "Sub de Pavo", "Pan de 15cm con vegetales", "https://images.unsplash.com/photo-1592415499556-74fcb9f18667?w=400", 5.75),
                Dish(26, "Sub Italiano BMT", "Pepperoni, salami y jamón", "https://images.unsplash.com/photo-1509722747041-616f39b57569?w=400", 6.50),
                Dish(27, "Wrap de Pollo", "Pollo grillé con lechuga", "https://images.unsplash.com/photo-1550304943-4f24f54ddde9?w=400", 5.99)
            )
        ),
        Restaurant(
            id = 10, name = "Buffalo Wings",
            description = "Expertos en alitas desde 1994",
            imageUrl = "https://images.unsplash.com/photo-1567620832903-9fc6debc209f?w=500",
            categories = listOf("Rápida", "Alitas"),
            menu = listOf(
                Dish(28, "10 Alitas Atomic", "Picante extremo", "https://images.unsplash.com/photo-1527477396000-e27163b481c2?w=400", 8.99),
                Dish(29, "10 Alitas BBQ", "Salsa dulce ahumada", "https://images.unsplash.com/photo-1567620832903-9fc6debc209f?w=400", 8.99),
                Dish(30, "Boneless Honey", "Sin hueso con miel mostaza", "https://images.unsplash.com/photo-1562967914-608f82629710?w=400", 7.50)
            )
        ),
        Restaurant(
            id = 11, name = "Tony Roma's",
            description = "Famosos por sus costillas desde 1972",
            imageUrl = "https://images.unsplash.com/photo-1529193591184-b1d58069ecdd?w=500",
            categories = listOf("Carnes", "Costillas"),
            menu = listOf(
                Dish(31, "Baby Back Ribs", "Costillas con salsa BBQ original", "https://images.unsplash.com/photo-1529193591184-b1d58069ecdd?w=400", 17.99),
                Dish(32, "Full Rack", "Costillas completas para compartir", "https://images.unsplash.com/photo-1544025162-d76694265947?w=400", 24.99),
                Dish(33, "Combo Costillas", "Media rack con papas", "https://media.istockphoto.com/id/1988357787/photo/roasted-rack-of-lamb.jpg?s=1024x1024&w=is&k=20&c=KyjZJIuYoHnjhKZC-kgzu20-_X-ayyryOeOHCz91KuQ=", 15.50)
            )
        ),
        Restaurant(
            id = 12, name = "Go Green",
            description = "Alimentación saludable y fresca",
            imageUrl = "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?w=500",
            categories = listOf("Saludable", "Ensaladas"),
            menu = listOf(
                Dish(34, "Ensalada César", "Pollo grillé con aderezo César", "https://images.unsplash.com/photo-1550304943-4f24f54ddde9?w=400", 6.99),
                Dish(35, "Bowl Proteico", "Quinoa, pechuga y aguacate", "https://images.unsplash.com/photo-1597958792579-bd3517df6399?q=80&w=688&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 8.50),
                Dish(36, "Smoothie Verde", "Espinaca, manzana y jengibre", "https://plus.unsplash.com/premium_photo-1700084621249-b22c621ac4e9?q=80&w=721&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 4.25)
            )
        )
    )

    override fun getRestaurants(): List<Restaurant> = restaurants

    override fun getRestaurantById(id: Int): Restaurant? =
        restaurants.find { it.id == id }

    override fun searchRestaurants(query: String): List<Restaurant> {
        val q = query.normalize()
        if (q.isEmpty()) return emptyList()
        return restaurants.filter { restaurant ->
            restaurant.name.normalize().contains(q) ||
                    restaurant.description.normalize().contains(q) ||
                    restaurant.categories.any { it.normalize().contains(q) } ||
                    restaurant.menu.any { dish ->
                        dish.name.normalize().contains(q) ||
                                dish.description.normalize().contains(q)
                    }
        }
    }
}