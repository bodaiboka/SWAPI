package com.rbodai.icellswapi.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.rbodai.icellswapi.R
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
                    MainScreen {
                        PlanetListView() {

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(content: @Composable () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.spacebg),
        contentDescription = "background image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )
    content()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SWAPITheme {
        MainScreen {
            PlanetListView() {

            }
        }
    }
}