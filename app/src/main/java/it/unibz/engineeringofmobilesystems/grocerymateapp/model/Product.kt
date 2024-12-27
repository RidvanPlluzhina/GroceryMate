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
    val nutriments: Nutriments? // Add this field for energy_100g
)

// Declared energy_kcal_value as part of nutriments
data class Nutriments(
    @SerializedName("energy-kcal_value") val energy_kcal_value: Int?
)

