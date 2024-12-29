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

    // Fetch products by their barcode
    fun fetchProductsByBarcodes(barcodes: List<String>) {
        viewModelScope.launch {
            val fetchedProducts = mutableListOf<Product>()
            for (barcode in barcodes) {
                try {
                    val response = RetrofitInstance.api.getProductByBarcode(barcode)
                    response.product?.let { product ->
                        println("Fetched product: $product")
                        println("Full API response: $response")
                        fetchedProducts.add(product)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            _products.value = fetchedProducts
        }
    }
}

