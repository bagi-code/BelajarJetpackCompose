package com.bagicode.myjetpackcompose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bagicode.myjetpackcompose.utils.materials.EmailInputField
import com.bagicode.myjetpackcompose.utils.materials.PasswordInputField
import com.bagicode.myjetpackcompose.utils.materials.SubmitButton


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FormScreen()
        }
    }
}

@Composable
fun FormScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center, // Mengatur tata letak vertikal menjadi tengah (center)
        horizontalAlignment = Alignment.CenterHorizontally // Mengatur tata letak horizontal menjadi tengah (center)

    ) {
        EmailInputField(onEmailChange = { email = it })
        PasswordInputField(onPasswordChange = { password = it })
        SubmitButton(
            onSubmitClick = { navigateToHome(context) },
            label = "Login"
        )
    }
}

private fun navigateToHome(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultLoginPreview() {
    FormScreen()
}