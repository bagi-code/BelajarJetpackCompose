package com.bagicode.myjetpackcompose.utils.materials

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bagicode.myjetpackcompose.R

@Composable
fun ImageView(
    sizeDp : Dp,
    url : Int,
    desc : String,
    contentScale: ContentScale
) {
    Image(
        painter = painterResource(url),
        contentDescription = desc,
        modifier = Modifier
            .size(sizeDp)
            .fillMaxSize(),
        contentScale = contentScale
    )
}