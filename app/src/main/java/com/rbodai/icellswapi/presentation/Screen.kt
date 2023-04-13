package com.rbodai.icellswapi.presentation

sealed class Screen(val itineraire : String) {
    object PlanetListScreen : Screen("planet_list_screen")
    object PlanetInfoScreen : Screen("planet_info_screen")
}