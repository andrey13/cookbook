package com.example.cookbook.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.presentation.composables.ScrEditData
import com.example.cookbook.presentation.composables.ScrHome
import com.example.cookbook.presentation.composables.ScrList
import com.example.cookbook.presentation.composables.ScrSplash
import com.example.cookbook.presentation.composables.ScrTest
import com.example.cookbook.viewmodels.CookViewModel

@Composable
fun NavGraph(vm: CookViewModel = hiltViewModel()) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
//        startDestination = NavRoutes.Test.route,
//        startDestination = NavRoutes.Splash.route,
        startDestination = NavRoutes.Splash.route,
    ) {
        //-------------------------------------------------------------------------------
        composable(NavRoutes.Test.route) {
            ScrTest(nc = navController)
        }

        //-------------------------------------------------------------------------------
        composable(NavRoutes.Splash.route) {
            ScrSplash(nc = navController)
        }

        //-------------------------------------------------------------------------------
        composable(NavRoutes.Home.route) {
            ScrHome(nc = navController, vm)
        }

        //-------------------------------------------------------------------------------
        composable(NavRoutes.AddData.route + "/{index}") { backStackEntry ->

            val index = backStackEntry.arguments?.getString("index")?.toInt()

            if (index != null) {
                ScrEditData(
                    nc = navController,
                    vm,
                    index,
                    "",
                    "NEW")
            } else {
                ScrEditData(
                    nc = navController,
                    vm,
                    0,
                    "",
                    "NEW"
                )
            }
        }

        //-------------------------------------------------------------------------------
        composable(NavRoutes.EditData.route + "/{index}/{name}/{id}") { backStackEntry ->

            val index = backStackEntry.arguments?.getString("index")?.toInt()
            val name = backStackEntry.arguments?.getString("name") ?: "?"
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0

            if (index != null) {
                ScrEditData(
                    nc = navController,
                    vm,
                    index,
                    name,
                    "EDIT",
                    id = id
                )
            } else {
                ScrEditData(
                    nc = navController,
                    vm,
                    0,
                    "",
                    "EDIT"
                )
            }
        }

    }
}
