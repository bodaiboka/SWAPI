package com.rbodai.icellswapi.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rbodai.icellswapi.presentation.views.PlanetListView
import com.rbodai.icellswapi.ui.theme.SWAPITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SWAPITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PlanetListView()
                }
            }
        }
    }
}

@Composable
fun MainScreen(content: @Composable () -> Unit) {
    content()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SWAPITheme {
        PlanetListView()
    }
}