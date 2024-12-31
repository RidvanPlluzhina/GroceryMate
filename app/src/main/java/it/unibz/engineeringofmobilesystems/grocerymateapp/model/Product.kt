package it.unibz.engineeringofmobilesystems.grocerymateapp.model

import com.google.gson.annotations.SerializedName

data class FoodResponse(
    val products: List<Product>
)

// https://world.openfoodfacts.org/api/v0/product/{barcode here}.json

data class Product(
    val product_name: String,
    val image_url: String?,
    val quantity: String?,
    val brands: String?,
    val nutriments: Nutriments?
)

// Declared energy_kcal_value as part of nutriments
data class Nutriments(
    @SerializedName("energy-kcal_value") val energy_kcal_value: Int?,
    val fat_100g: Int?,
    val sugars_100g: Int?,
    val proteins_value: Int?
)

