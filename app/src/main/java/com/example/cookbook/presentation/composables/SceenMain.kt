package com.example.cookbook.presentation.composables

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.presentation.NavRoutes
import com.example.cookbook.viewmodels.CookViewModel

@Composable
fun ScreenMain(vm: CookViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ) {

        composable(NavRoutes.AddData.route + "/{index}") { backStackEntry ->

            val index = backStackEntry.arguments?.getString("index")?.toInt()

            if (index != null) {
                ScreenEditData(nc = navController, vm, index, "", "NEW")
            } else {
                ScreenEditData(nc = navController, vm, 0, "", "NEW")
            }
        }

        composable(NavRoutes.EditData.route + "/{index}/{name}/{id}") { backStackEntry ->

            val index = backStackEntry.arguments?.getString("index")?.toInt()
            val name = backStackEntry.arguments?.getString("name") ?: "?"
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0

            if (index != null) {
                ScreenEditData(nc = navController, vm, index, name, "EDIT", id)
            } else {
                ScreenEditData(nc = navController, vm, 0, "", "EDIT")
            }
        }

        composable(NavRoutes.Home.route) {
                ScreenHome(nc = navController, vm)
            }

    }
}
