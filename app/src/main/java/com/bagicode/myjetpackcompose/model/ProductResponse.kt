package com.bagicode.myjetpackcompose.model


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("products")
    val products: List<ProductItem>?,
    @SerializedName("skip")
    val skip: Int?,
    @SerializedName("total")
    val total: Int?
) {
    data class ProductItem(
        @SerializedName("brand")
        val brand: String?,
        @SerializedName("category")
        val category: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("discountPercentage")
        val discountPercentage: Double?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("images")
        val images: List<String>?,
        @SerializedName("price")
        val price: Int?,
        @SerializedName("rating")
        val rating: Double?,
        @SerializedName("stock")
        val stock: Int?,
        @SerializedName("thumbnail")
        val thumbnail: String?,
        @SerializedName("title")
        val title: String?
    )
}