package com.example.cookbook.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.presentation.composables.ScreenEditData
import com.example.cookbook.presentation.composables.ScreenHome
import com.example.cookbook.presentation.composables.Splash
import com.example.cookbook.viewmodels.CookViewModel

@Composable
fun NavGraph(vm: CookViewModel = hiltViewModel()) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash.route,
    ) {
        //-------------------------------------------------------------------------------
        composable(NavRoutes.Splash.route) {
            Splash(nc = navController)
        }

        //-------------------------------------------------------------------------------
        composable(NavRoutes.Home.route) {
            ScreenHome(nc = navController, vm)
        }

        //-------------------------------------------------------------------------------
        composable(NavRoutes.AddData.route + "/{index}") { backStackEntry ->

            val index = backStackEntry.arguments?.getString("index")?.toInt()

            if (index != null) {
                ScreenEditData(nc = navController, vm, index, "", "NEW")
            } else {
                ScreenEditData(nc = navController, vm, 0, "", "NEW")
            }
        }

        //-------------------------------------------------------------------------------
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

    }
}
