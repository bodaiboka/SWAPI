package com.rbodai.icellswapi.presentation.views.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

@Composable
fun ItemTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h2,
        color = MaterialTheme.colors.secondary
    )
}

@Composable
fun ItemTitleIcon(imageUrl: String) {
    Image(
        painter = rememberImagePainter(data = imageUrl),
        contentDescription = null,
        modifier = Modifier
            .height(32.dp),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun ItemDetailIcon(icon: ImageVector, description: String) {
    Icon(imageVector = icon,
        contentDescription = description,
        tint = MaterialTheme.colors.secondary
    )
}

@Composable
fun ItemDetailLabel(detail: String) {
    Text(
        text = detail,
        fontSize = 10.sp,
        color = MaterialTheme.colors.secondary
    )
}

@Composable
fun ItemCard(itemId: Int, title: String, detail1: String, detail2: String, icon1: ImageVector, icon2: ImageVector, imageUrl: String, onElementClick: (id: Int) -> Unit) {
    Row(
        modifier = Modifier
            .height(100.dp)
            .clickable { onElementClick(itemId) },
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
                ItemTitle(title = title)
                Spacer(modifier = Modifier.width(10.dp))
                ItemTitleIcon(imageUrl = imageUrl)
            }

            Row(
                modifier = Modifier
                    .fillMaxHeight(.5f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ItemDetailIcon(icon1, detail1)
                ItemDetailLabel(detail1)
                Spacer(modifier = Modifier.width(24.dp))
                ItemDetailIcon(icon2, detail2)
                ItemDetailLabel(detail2)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = "Details",
                tint = MaterialTheme.colors.primaryVariant
            )
        }
    }
}

@Composable
fun DetailsInfo(label: String, value: String) {
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
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
    }
}

@Composable
fun DetailsTitle(modifier: Modifier, title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.secondary,
        modifier = modifier
    )
}

@Composable
fun DetailsInfoSection(infoList: List<String>) {
    for (i in infoList) {
        val info = i.split("*")
        DetailsInfo(label = info[0], value = info[1])
    }
}

@Composable
fun DeatilsCard(title: String, info: List<String>, CustomContent: @Composable() () -> Unit) {
    Row(
        modifier = Modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically

    ) {
        BlueCard {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomContent()
                DetailsTitle(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally), title)
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )
                DetailsInfoSection(info)
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )
            }
        }
    }
}