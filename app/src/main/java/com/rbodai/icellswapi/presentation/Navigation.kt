package com.rbodai.icellswapi.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rbodai.icellswapi.presentation.views.PlanetInfoView
import com.rbodai.icellswapi.presentation.views.PlanetListView
import com.rbodai.icellswapi.presentation.views.ShipInfoView
import com.rbodai.icellswapi.presentation.views.ShipListView

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.ShipListScreen.itineraire) {
        composable(route = Screen.PlanetListScreen.itineraire) {
            PlanetListView(navController = navController)
        }
        composable(
            route = Screen.PlanetInfoScreen.itineraire + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            PlanetInfoView(id = it.arguments?.getInt("id")!!)
        }
        composable(route = Screen.ShipListScreen.itineraire) {
            ShipListView(navController = navController)
        }
        composable(
            route = Screen.ShipInfoScreen.itineraire + "/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) {
            ShipInfoView()
        }
    }
}