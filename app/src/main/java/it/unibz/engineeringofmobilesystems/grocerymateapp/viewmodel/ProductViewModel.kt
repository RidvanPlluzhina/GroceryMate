package it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.Product
import it.unibz.engineeringofmobilesystems.grocerymateapp.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    // Fetch products by their barcode
    fun fetchProductsByBarcodes(barcodes: List<String>) {
        viewModelScope.launch {
            val fetchedProducts = mutableListOf<Product>()
            for (barcode in barcodes) {
                try {
                    val response = RetrofitInstance.api.getProductByBarcode(barcode)
                    response.product?.let { fetchedProducts.add(it) }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            _products.value = fetchedProducts
        }
    }
}

