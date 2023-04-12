package com.rbodai.icellswapi.presentation.views

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.rbodai.icellswapi.domain.model.Planet
import com.rbodai.icellswapi.presentation.viewmodels.PlanetListViewModel

@Composable
fun PlanetListView(viewModel: PlanetListViewModel = hiltViewModel()) {
    val res = viewModel.planets.value
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
            modifier = Modifier.fillMaxWidth()
        ) {
            items(it) {
                PlanetView(it)
            }
        }
    }
}

@Composable
fun PlanetView(planet: Planet) {
    Card(
        modifier = Modifier.padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier.height(100.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = rememberImagePainter(data = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/23/DesertPlanet.jpg/250px-DesertPlanet.jpg"),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = planet.name,
            )
        }
    }

}