package it.unibz.engineeringofmobilesystems.grocerymateapp.model

data class FoodResponse(
    val products: List<Product>
)

data class Product(
    val product_name: String,
    val image_url: String?,
    val quantity: String?,
    val brands: String?
) {

}

