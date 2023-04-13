package com.rbodai.icellswapi.presentation.navigation

sealed class Screens(val itineraire : String) {
    object PlanetInfoScreen : Screens("planet_info_screen")
    object ShipInfoScreen : Screens("ship_info_screen")
}