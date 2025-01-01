package it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.Product
import it.unibz.engineeringofmobilesystems.grocerymateapp.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _cartItems = MutableStateFlow<List<Product>>(emptyList())
    val cartItems: StateFlow<List<Product>> = _cartItems.asStateFlow()

    private val _favoritesItems = MutableStateFlow<List<Product>>(emptyList())
    val favoritesItems: StateFlow<List<Product>> = _favoritesItems.asStateFlow()


    fun addToCart(product: Product) {
        _cartItems.value = _cartItems.value + product
    }

    fun addToFav(product: Product) {
        _favoritesItems.value = _favoritesItems.value + product
    }

    fun removeFromCart(product: Product) {
        _cartItems.value = _cartItems.value.filter { it != product }
    }

    fun removeFromFavourites (product: Product) {
        _favoritesItems.value = _favoritesItems.value.filter  {it != product}
    }

    // Fetch products by their barcode
    fun fetchProductsByBarcodes(barcodes: List<String>) {
        viewModelScope.launch {
            val fetchedProducts = mutableListOf<Product>()
            for (barcode in barcodes) {
                try {
                    val response = RetrofitInstance.api.getProductByBarcode(barcode)
                    if (response.product != null) {
                        fetchedProducts.add(response.product)
                    } else {
                        println("No product found for barcode: $barcode")
                    }
                } catch (e: Exception) {
                    println("Error fetching product for barcode: $barcode - ${e.message}")
                }
            }
            // Update the products state flow
            if (fetchedProducts.isNotEmpty()) {
                _products.value = fetchedProducts
            } else {
                println("No products fetched.")
            }
        }
    }

}

