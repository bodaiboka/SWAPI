package com.rbodai.icellswapi.presentation.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.rbodai.icellswapi.domain.model.Ship
import com.rbodai.icellswapi.presentation.Screen
import com.rbodai.icellswapi.presentation.viewmodels.ShipListViewModel
import com.rbodai.icellswapi.presentation.viewmodels.ShipViewModel

@Composable
fun ShipListView(viewModel: ShipListViewModel = hiltViewModel(), navController: NavController) {
    val res = viewModel.ships.value
    if (res.isLoading) {
        Box(modifier = Modifier.fillMaxSize())
        {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
    if (res.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = res.error, modifier = Modifier.align(Alignment.Center))
        }
    }
    res.data?.let {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(it) {
                ShipCardView(ship = it) { id ->
                    navController.navigate(Screen.ShipInfoScreen.itineraire + "/" + id)
                }
            }
        }
    }
}

@Composable
fun ShipCardView(ship: Ship, onElementClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(start = 50.dp, end = 50.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.primary,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primaryVariant)
    ) {
        Row(
            modifier = Modifier
                .height(100.dp)
                .clickable { onElementClick(ship.id) },
            verticalAlignment = Alignment.CenterVertically,

            ) {

            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxHeight(.5f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = ship.name,
                        style = MaterialTheme.typography.h1
                    )
                    Image(
                        painter = rememberImagePainter(data = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/23/DesertPlanet.jpg/250px-DesertPlanet.jpg"),
                        contentDescription = null,
                        modifier = Modifier
                            .height(32.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxHeight(.5f),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(imageVector = Icons.Outlined.Person,
                        contentDescription = "population",

                        tint = Color.White
                    )
                    Text(
                        text = ship.crew,
                        fontSize = 10.sp,
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    Icon(imageVector = Icons.Outlined.LocationOn,
                        contentDescription = "diameter",

                        tint = Color.White
                    )
                    Text(
                        text = ship.length,
                        fontSize = 10.sp,
                    )
                }
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowRight,
                    contentDescription = "Details",
                    tint = MaterialTheme.colors.primaryVariant
                )
            }
        }
    }
}

@Composable
fun ShipInfoView(viewModel: ShipViewModel = hiltViewModel()) {

    val res = viewModel.ship.value
    if (res.isLoading) {
        Box(modifier = Modifier.fillMaxSize())
        {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
    if (res.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = res.error, modifier = Modifier.align(Alignment.Center))
        }
    }
    res.data?.let {
        val ship = it
        Row(
            modifier = Modifier.fillMaxHeight()
        ) {

            Card(
                shape = MaterialTheme.shapes.medium,
                elevation = 5.dp,
                backgroundColor = MaterialTheme.colors.primary,
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primaryVariant),
                modifier = Modifier
                    .padding(start = 50.dp, end = 50.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically)
            ) {

                Column(
                ) {
                    Image(
                        painter = rememberImagePainter(data = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/23/DesertPlanet.jpg/250px-DesertPlanet.jpg"),
                        contentDescription = null,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = ship.name,
                        style = MaterialTheme.typography.h1,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                    )
                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(alignment = Alignment.Start),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Crew",
                            style = MaterialTheme.typography.h2,
                            color = MaterialTheme.colors.primaryVariant,
                        )
                        Text(
                            text = ship.crew,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.secondary,
                        )
                    }

                }
            }
        }
    }

}