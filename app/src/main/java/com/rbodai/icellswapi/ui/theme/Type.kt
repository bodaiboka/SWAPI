package com.rbodai.icellswapi.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rbodai.icellswapi.R

// Set of Material typography styles to start with

val Orbitron = FontFamily(
    Font(R.font.orbitron)
)

val MontserratLight = FontFamily(
    Font(R.font.montserrat_light)
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Orbitron,
        fontSize = 24.sp
    ),

    h2 = TextStyle(
        fontFamily = Orbitron,
        fontSize = 18.sp
    ),

    body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    body1 = TextStyle(
        fontFamily = MontserratLight,
        fontSize = 12.sp
    ),

    h3 = TextStyle(
        fontFamily = Orbitron,
        fontSize = 10.sp
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)