package com.bagicode.myjetpackcompose.module.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagicode.myjetpackcompose.model.ProductResponse
import com.bagicode.myjetpackcompose.repository.NetworkService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _listProduct = mutableStateOf<List<ProductResponse.ProductItem>>(emptyList())
    val listProduct: State<List<ProductResponse.ProductItem>> = _listProduct

    fun getProduct() {
        viewModelScope.launch {
            try {
                val response = NetworkService.product()
                _listProduct.value = response.products ?: listOf()
            } catch (e: Exception) {
                // Tangani kesalahan, misalnya menampilkan pesan kesalahan
            }
        }
    }
}
