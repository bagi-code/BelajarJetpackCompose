package com.bagicode.myjetpackcompose.module.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val title = intent.getStringExtra("title").orEmpty()
        val url = intent.getStringExtra("url").orEmpty()

        setContent {
            initView(title, url)
        }
    }
}

@Composable
fun CoilImage(url: String, modifier: Modifier) {
    val imagePainter: Painter = rememberImagePainter(data = url)

//    AsyncImage(
//        model = url,
//        contentDescription = null,
//    )

    Image(
        painter = imagePainter,
        contentDescription = null, // Jangan lupa untuk memberikan deskripsi aksesibilitas yang tepat jika digunakan dalam aplikasi yang sebenarnya
        modifier = modifier,
        contentScale = ContentScale.Fit
    )
}

@Composable
fun initView(title : String, url: String) {
    Column {
        CoilImage(
            url = url,
            modifier = Modifier
                .size(200.dp)
                .clickable {
                    // Aksi yang ingin dijalankan ketika item/gambar diklik
                }
        )
        Text(
            text = title,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )
    }
}

//@OptIn(ExperimentalCoilApi::class)
//@Composable
//fun CoilAsyncImage(url: String, modifier: Modifier) {
//    AsyncImage(
//        painter = rememberImagePainter(data = url),
//        contentDescription = null, // Jangan lupa untuk memberikan deskripsi aksesibilitas yang tepat jika digunakan dalam aplikasi yang sebenarnya
//        modifier = modifier,
//        contentScale = ContentScale.Fit,
//        loading = {
//            // Tampilan ini akan ditampilkan saat gambar masih dalam proses pemuatan
//            // Anda dapat menambahkan indikator pemuatan atau placeholder di sini
//        },
//        error = {
//            // Tampilan ini akan ditampilkan jika terjadi kesalahan saat memuat gambar
//            // Anda dapat menambahkan pesan kesalahan atau placeholder kesalahan di sini
//        }
//    )
//}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun DefaultPreviewStarting() {
//    ShowIconApps()
//}
