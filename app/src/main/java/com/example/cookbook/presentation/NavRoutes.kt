package com.example.cookbook.presentation

sealed class NavRoutes(val route: String) {
    object Splash : NavRoutes("splash")
    object Home : NavRoutes("home")
    object AddData : NavRoutes("add_data")
    object EditData : NavRoutes("edit_data")
}
