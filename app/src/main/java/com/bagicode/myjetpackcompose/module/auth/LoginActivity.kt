package com.bagicode.myjetpackcompose.module.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.bagicode.myjetpackcompose.MainActivity
import com.bagicode.myjetpackcompose.utils.materials.EmailInputField
import com.bagicode.myjetpackcompose.utils.materials.PasswordInputField
import com.bagicode.myjetpackcompose.utils.materials.SubmitButton


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val loginViewModel: LoginViewModel by viewModels()

            val isLoggedIn: Boolean by loginViewModel.isLoggedIn.collectAsState()
            FormScreen(viewModel = loginViewModel)

            if (isLoggedIn) {
                // Jika isLoggedIn bernilai true, maka berpindah ke Activity berikutnya
                // Misalnya, MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Tutup Activity ini agar tidak dapat kembali lagi dengan tombol back
            }

            val isLoading: Boolean by loginViewModel.isLoading.collectAsState()
            if(isLoading){
                //CircularProgressIndicator()
                ProgressDialog()
            }
        }
    }
}

@Composable
fun FormScreen(viewModel: LoginViewModel) {
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
            onSubmitClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        //viewModel.onLogin(email, password) <- klo mau sesuai field
                        viewModel.onLogin("kminchelle", "0lelplR") // khusus yg malas nulis
                    } else {
                        Toast.makeText(context, "Silahkan isi email dan password", Toast.LENGTH_LONG).show()
                    }
                },
            label = "Login"
        )
    }
}


@Composable
fun ProgressDialog() {
    val context = LocalContext.current
    AlertDialog(
        onDismissRequest = { /* Tidak melakukan apa pun ketika dialog ditutup */ },
        title = { Text("Loading...") },
        text = {
            // Tampilkan indikator progres di dalam dialog
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .padding(16.dp)
            )
        },
        buttons = {
            /* Tidak menampilkan tombol apa pun di dalam dialog */
        },
        properties = DialogProperties(dismissOnClickOutside = false)
    )
}