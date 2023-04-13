package com.rbodai.icellswapi.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rbodai.icellswapi.presentation.views.MainScreen
import com.rbodai.icellswapi.ui.theme.SWAPITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SWAPITheme {
                MainScreen()
            }
        }
    }
}

//@Composable
//fun MainScreen(content: @Composable () -> Unit) {
//    Image(
//        painter = painterResource(id = R.drawable.spacebg),
//        contentDescription = "background image",
//        contentScale = ContentScale.Crop,
//        modifier = Modifier
//            .fillMaxSize()
//    )
//    Box(modifier = Modifier
//        .padding(top = 20.dp, bottom = 20.dp)) {
//        content()
//    }
//}
