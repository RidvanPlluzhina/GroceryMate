package it.unibz.engineeringofmobilesystems.grocerymateapp.model

import com.google.gson.annotations.SerializedName

data class FoodResponse(
    val products: List<Product>
)

// https://world.openfoodfacts.org/api/v0/product/{barcode here}.json

// Represents the details of a single product in the API response.
data class Product(
    val product_name: String,
    val image_url: String?,
    val quantity: String?,
    val brands: String?,
    val nutriments: Nutriments?
)

// Represents the nutritional information of a product.
    data class Nutriments(
    @SerializedName("energy-kcal_value") val energy_kcal_value: Float?,
    val fat_100g: Float?,
    val sugars_100g: Float?,
    val proteins_value: Float?
)

