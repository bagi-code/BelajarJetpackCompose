package com.bagicode.myjetpackcompose.module.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagicode.myjetpackcompose.repository.NetworkService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun onLogin(username: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = NetworkService.login(username, password)
                // Proses respon login dan set isLoggedIn menjadi true jika berhasil
                _isLoggedIn.value = true
                _isLoading.value = false
            } catch (e: Exception) {
                // Tangani kesalahan, misalnya menampilkan pesan kesalahan
            }
        }
    }
}
