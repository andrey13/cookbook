package com.example.cookbook.presentation

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object AddDish : NavRoutes("add_dish")
}
