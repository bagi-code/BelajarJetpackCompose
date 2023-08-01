package com.bagicode.myjetpackcompose.module.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val title = intent.getStringExtra("title").orEmpty()

        setContent {
            initView(title)
        }
    }
}

@Composable
fun initView(title : String) {
    Text(text = "title ${title}")
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .wrapContentSize(Alignment.Center)
//    ) {
//        Image(
//            painter = painterResource(R.drawable.android_logo),
//            contentDescription = "icon apps",
//            modifier = Modifier
//                .size(150.dp)
//                .align(Alignment.Center)
//                .fillMaxSize(),
//        )
//    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun DefaultPreviewStarting() {
//    ShowIconApps()
//}
