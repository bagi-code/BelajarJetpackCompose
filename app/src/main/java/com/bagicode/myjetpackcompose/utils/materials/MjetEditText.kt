package com.bagicode.myjetpackcompose.utils.materials

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun EmailInputField(onEmailChange: (String) -> Unit) {
    var name by remember { mutableStateOf("") }

    OutlinedTextField(
        value = name,
        onValueChange = {
            name = it
            onEmailChange(it)
        },
        label = { Text("Username") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PasswordInputField(onPasswordChange: (String) -> Unit) {
    var email by remember { mutableStateOf("") }

    OutlinedTextField(
        value = email,
        onValueChange = {
            email = it
            onPasswordChange(it)
        },
        label = { Text("Password") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        modifier = Modifier.fillMaxWidth()
    )
}