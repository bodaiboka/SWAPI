package com.rbodai.icellswapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rbodai.icellswapi.presentation.views.PlanetInfoView
import com.rbodai.icellswapi.presentation.views.PlanetListView
import com.rbodai.icellswapi.presentation.views.ShipInfoView
import com.rbodai.icellswapi.presentation.views.ShipListView

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = "home",
        startDestination = BottomBarScreen.Planets.route) {
        composable(route = BottomBarScreen.Planets.route) {
            PlanetListView(navController = navController)
        }
        composable(
            route = Screens.PlanetInfoScreen.itineraire + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            PlanetInfoView(id = it.arguments?.getInt("id")!!)
        }
        composable(route = BottomBarScreen.Ships.route) {
            ShipListView(navController = navController)
        }
        composable(
            route = Screens.ShipInfoScreen.itineraire + "/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) {
            ShipInfoView()
        }
    }
}