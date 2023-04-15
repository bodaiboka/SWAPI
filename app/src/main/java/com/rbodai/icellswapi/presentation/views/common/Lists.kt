package com.rbodai.icellswapi.presentation.views.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavController
import com.rbodai.icellswapi.presentation.navigation.Screens
import com.rbodai.icellswapi.presentation.views.PlanetCardView
import com.rbodai.icellswapi.presentation.viewstates.ListViewStateHolder

@Composable
fun <T, V: Any> SWList(res: ListViewStateHolder<T>, mapKeyData: Map<Int, V>?, navController: NavController) {
    ResponseWrapper(res = res) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(mapKeyData?.toList()!!) {
                    PlanetCardView(id = it.first) { id ->
                        navController.navigate(Screens.PlanetInfoScreen.itineraire + "/" + id)
                    }
                }
            }
        }
    }
}