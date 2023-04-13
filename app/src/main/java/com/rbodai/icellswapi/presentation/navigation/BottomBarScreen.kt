package com.rbodai.icellswapi.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Planets : BottomBarScreen(
        route = "PLANETS",
        title = "Planets",
        icon = Icons.Default.Star
    )

    object Ships : BottomBarScreen(
        route = "SHIPS",
        title = "Ships",
        icon = Icons.Default.Person
    )

}
