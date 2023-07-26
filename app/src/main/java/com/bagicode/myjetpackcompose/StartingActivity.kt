package com.bagicode.myjetpackcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bagicode.myjetpackcompose.module.auth.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShowIconApps()
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            val intent = Intent(this@StartingActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

@Composable
fun ShowIconApps() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Image(
            painter = painterResource(R.drawable.android_logo),
            contentDescription = "icon apps",
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center)
                .fillMaxSize(),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DefaultPreviewStarting() {
    ShowIconApps()
}
