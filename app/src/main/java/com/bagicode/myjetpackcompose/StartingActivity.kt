package com.bagicode.myjetpackcompose

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            showIconApps()
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            val intent = Intent(this@StartingActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

@Composable
fun showIconApps() {
    Image(
        painter = painterResource(R.drawable.logo_android),
        contentDescription = "Logo apps",
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DefaultPreviewStarting() {
    showIconApps()
}
