# FoodSpot

Aplicación móvil desarrollada en Android con Jetpack Compose para explorar restaurantes locales, ver sus menús y realizar pedidos.

> **Taller 2 — Programación de Dispositivos Móviles | UCA 2026**

## 📱 Video demo

[![Explicacion de la app](https://img.youtube.com/vi/v1_B049aVlw/0.jpg)](https://youtu.be/v1_B049aVlw)

---

## 🏗️ Arquitectura

El proyecto implementa **MVVM con Repository** siguiendo las buenas prácticas de Android:

com.pdm0126.foodspoot/
├── data/               → Interface e implementación del Repository
├── model/              → Data classes (Restaurant, Dish, CartItem)
├── navigation/         → Rutas y navegación con Navigation 3
└── screens/
├── Home/           → HomeScreen + HomeViewModel
├── Detail/         → DetailScreen + DetailViewModel
├── Search/         → SearchScreen + SearchViewModel
├── Cart/           → CartScreen + CartViewModel
└── OrderSuccess/   → Pantalla de confirmación

- **ViewModel** expone estado con `StateFlow` inmutable vía `asStateFlow()`
- **Repository** provee los datos; los Composables solo observan con `collectAsState()`
- **CartViewModel** y **FavoritesViewModel** se comparten entre pantallas desde `MainActivity`

---

## Funcionalidades

### Requeridas
-  Lista de restaurantes agrupados dinámicamente por categoría
-  Detalle del restaurante con menú completo y Toast al agregar
-  Búsqueda por nombre de restaurante o platillo (case-insensitive, sin acentos)

### Extras
-  Carrito con badge, subtotal en tiempo real y método de pago
-  Pantalla de confirmación de compra con resumen
-  Favoritos con sección dedicada en Home
-  Filtro de precio con ModalBottomSheet

---

##  Stack

- Kotlin
- Jetpack Compose
- Navigation 3
- ViewModel + StateFlow
- Coil (carga de imágenes)
- Material 3
