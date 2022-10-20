package com.example.cookbook.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.presentation.NavRoutes
import com.example.cookbook.viewmodels.CookViewModel

@Composable
fun ScreenMain(vm: CookViewModel?) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ) {

        composable(NavRoutes.AddDish.route + "/{index}") {backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")?.toInt()
            if (index != null) {
                ScreenAddDish(nc = navController, vm, index)
            } else {
                ScreenAddDish(nc = navController, vm, 0)
            }
        }

        composable(NavRoutes.Home.route) {
                ScreenHome(nc = navController, vm)
            }

    }
}
