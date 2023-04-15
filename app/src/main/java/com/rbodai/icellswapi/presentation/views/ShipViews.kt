package com.rbodai.icellswapi.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rbodai.icellswapi.domain.model.Ship
import com.rbodai.icellswapi.presentation.navigation.Screens
import com.rbodai.icellswapi.presentation.viewmodels.ShipListViewModel
import com.rbodai.icellswapi.presentation.viewmodels.ShipViewModel
import com.rbodai.icellswapi.presentation.views.common.*

@Composable
fun ShipListView(viewModel: ShipListViewModel = hiltViewModel(), navController: NavController) {
    val res = viewModel.ships.value
    if (res.isLoading) {
        LoadingMessage()
    }
    if (res.error.isNotBlank()) {
        ErrorMessage(error = res.error)
    }
    res.data?.let {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(it) {
                ShipCardView(ship = it) { id ->
                    navController.navigate(Screens.ShipInfoScreen.itineraire + "/" + id)
                }
            }
        }
    }
}

@Composable
fun ShipCardView(ship: Ship, onElementClick: (id: Int) -> Unit) {
    BlueCard {
        ItemCard(
            itemId = ship.id,
            title = ship.name,
            detail1 = ship.crew,
            detail2 = ship.length + " m",
            icon1 = Icons.Outlined.Person,
            icon2 = Icons.Outlined.LocationOn,
            imageUrl = "https://www.awicons.com/free-icons/download/tv-movie-icons/star-wars-icons-by-archigraphs/png/128/XWing_archigraphs.png",
            onElementClick = onElementClick
        )
    }
}

@Composable
fun Ship3DView() {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        factory = { context ->
            G3DView(context)
        })
}

@Composable
fun ShipInfoView(viewModel: ShipViewModel = hiltViewModel()) {
    val res = viewModel.ship.value
    if (res.isLoading) {
        LoadingMessage()
    }
    if (res.error.isNotBlank()) {
        ErrorMessage(error = res.error)
    }
    res.data?.let {
        val ship = it
        val infoList = listOf<String>(
            "Starship class*" + ship.starship_class,
            "Model*" + ship.model,
            "Crew*" + ship.crew,
            "Length*" + ship.length + " m",
            "Cost in credits*" + ship.cost_in_credits,
        )
        DeatilsCard(title = ship.name, info = infoList) {
            Ship3DView()
        }
    }

}