package com.rbodai.icellswapi.presentation.views.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp

@Composable
fun BlueCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .padding(start = 50.dp, end = 50.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primaryVariant)
    ) {
        content()
    }
}

@Composable
fun RedCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .padding(start = 50.dp, end = 50.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.onBackground,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onSurface)
    ) {
        content()
    }
}

@Composable
fun LoadingMessage() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        BlueCard {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Loading content...",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary)
                CircularProgressIndicator(
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .scale(0.5f)
                )
            }
        }
    }
}

@Composable
fun ErrorMessage(error: String) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        RedCard {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = error,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary)
            }
        }
    }
}

