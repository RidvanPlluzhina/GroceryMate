package it.unibz.engineeringofmobilesystems.grocerymateapp.model

import com.google.gson.annotations.SerializedName

data class FoodResponse(
    val products: List<Product>
)

data class Product(
    val product_name: String,
    val image_url: String?,
    val quantity: String?,
    val brands: String?,
)
