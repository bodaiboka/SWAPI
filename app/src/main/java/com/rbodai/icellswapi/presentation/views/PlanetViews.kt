package com.rbodai.icellswapi.presentation.views

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.rbodai.icellswapi.presentation.viewmodels.PlanetListViewModel
import androidx.compose.material.icons.outlined.Info
import com.rbodai.icellswapi.presentation.views.common.*

@Composable
fun PlanetListView(viewModel: PlanetListViewModel = hiltViewModel(), navController: NavController) {
    val res = viewModel.planets.value
    SWList(res = res, mapKeyData = res.data, navController = navController)
}

@Composable
fun PlanetCardView(
    viewModel: PlanetListViewModel = hiltViewModel(),
    id: Int,
    onElementClick: (Int) -> Unit
) {
    val planet = viewModel.planets.value.data?.get(id)!!
    BlueCard {
        ItemCard(
            itemId = planet.id,
            title = planet.name,
            detail1 = planet.population,
            detail2 = planet.diameter + " km",
            icon1 = Icons.Outlined.Person,
            icon2 = Icons.Outlined.Info,
            imageUrl = "https://jkhub.org/wiki/images/0/01/Tatooine.png",
            onElementClick = onElementClick
        )
    }
}

@Composable
fun PlanetInfoView(viewModel: PlanetListViewModel = hiltViewModel(), id: Int) {

    val res = viewModel.planets.value
    ResponseWrapper(res = res) {
        val planet = it.get(id)!!
        val infoList = listOf<String>(
            "Population*" + planet.population,
            "Diameter*" + planet.diameter,
            "Terrain*" + planet.terrain,
            "Climate*" + planet.climate,
            "Orbital period*" + planet.orbital_period,
            "Rotation period*" + planet.rotation_period
        )
        DeatilsCard(planet.name, infoList) {
            Image(
                painter = rememberImagePainter(data = "https://jkhub.org/wiki/images/0/01/Tatooine.png"),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
        }
    }
}

