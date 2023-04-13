package com.rbodai.icellswapi.presentation.views

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
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
import com.rbodai.icellswapi.presentation.navigation.Screens
import com.rbodai.icellswapi.presentation.viewmodels.PlanetListViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.outlined.Info

@Composable
fun PlanetListView(viewModel: PlanetListViewModel = hiltViewModel(), navController: NavController) {
    val res = viewModel.planets.value
    if (res.isLoading) {
        LoadingMessage()
    }
    if (res.error.isNotBlank()) {
        ErrorMessage(res.error)

    }
    res.data?.let {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(it.toList()) {
                    PlanetCardView(id = it.first) { id ->
                        navController.navigate(Screens.PlanetInfoScreen.itineraire + "/" + id)
                    }
                }
            }
        }
    }
}

@Composable
fun PlanetCardView(viewModel: PlanetListViewModel = hiltViewModel(), id: Int, onElementClick: (Int) -> Unit) {
    val planet = viewModel.planets.value.data?.get(id)!!
    BlueCard {
        Row(
            modifier = Modifier
                .height(100.dp)
                .clickable { onElementClick(id) },
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
                        text = planet.name,
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.secondary
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = rememberImagePainter(data = "https://jkhub.org/wiki/images/0/01/Tatooine.png"),
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
                        text = planet.population,
                        fontSize = 10.sp,
                        color = MaterialTheme.colors.secondary
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    Icon(imageVector = Icons.Outlined.Info,
                        contentDescription = "diameter",

                        tint = Color.White
                    )
                    Text(
                        text = planet.diameter + " km",
                        fontSize = 10.sp,
                        color = MaterialTheme.colors.secondary
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
fun Info(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primaryVariant,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary,
        )
        Spacer(modifier = Modifier
            .height(10.dp))
    }
}

@Composable
fun PlanetInfoView(viewModel: PlanetListViewModel = hiltViewModel(), id: Int) {

    val res = viewModel.planets.value
    if (res.isLoading) {
        LoadingMessage()
    }
    if (res.error.isNotBlank()) {
        ErrorMessage(res.error)
    }
    res.data?.let {
        val planet = it.get(id)!!
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically

        ) {

            BlueCard() {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberImagePainter(data = "https://jkhub.org/wiki/images/0/01/Tatooine.png"),
                        contentDescription = null,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = planet.name,
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier
                        .height(20.dp))

                        Info(label = "Population", value = planet.population)
                        Info(label = "Diameter", value = planet.diameter)
                        Info(label = "Terrain", value = planet.terrain)
                        Info(label = "Climate", value = planet.climate)
                        Info(label = "Terrain", value = planet.terrain)
                        Info(label = "Climate", value = planet.climate)
                        Info(label = "Orbital period", value = planet.orbital_period)
                        Info(label = "Rotation period", value = planet.rotation_period)


                        Spacer(modifier = Modifier
                            .height(20.dp))
                    }

                }
            }
        }
    }

