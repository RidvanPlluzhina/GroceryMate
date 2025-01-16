package it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.CartItem
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.CounterItem
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.FavoriteItem
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.Product
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.data.CartItemDao
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.data.CounterItemDao
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.data.FavoriteItemDao
import it.unibz.engineeringofmobilesystems.grocerymateapp.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val cartItemDao: CartItemDao, // DAO for managing cart items in the database
    private val favoriteItemDao: FavoriteItemDao, // DAO for managing favorite items in the database
    private val counterItemDao: CounterItemDao  // DAO for managing counter items in the database
) : ViewModel() { // Extends ViewModel

    // Holds a list of products
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    // Public immutable flow for observing products
    val products: StateFlow<List<Product>> = _products

    // Holds cart items
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    // Exposed as an immutable flow
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    // Holds favorite items
    private val _favoriteItems = MutableStateFlow<List<FavoriteItem>>(emptyList())
    // Exposed as an immutable flow
    val favoriteItems: StateFlow<List<FavoriteItem>> = _favoriteItems.asStateFlow()

    // Holds counter items
    private val _counterItems = MutableStateFlow<List<CounterItem>>(emptyList())
    // Exposed as an immutable flow
    val counterItems: StateFlow<List<CounterItem>> = _counterItems.asStateFlow()

    init {
        // Load all cart items and update the StateFlow
        viewModelScope.launch {
            cartItemDao.getAllCartItems().collect { _cartItems.value = it }
        }

        // fav items
        viewModelScope.launch {
            favoriteItemDao.getAllFavoriteItems().collect { _favoriteItems.value = it }
        }

        // counter items
        viewModelScope.launch {
            counterItemDao.getAllCounterItems().collect { _counterItems.value = it }
        }
    }

    // fun to add to cart products
    fun addToCart(product: Product) {
        viewModelScope.launch {
            val cartItem = CartItem(
                productName = product.product_name,
                imageUrl = product.image_url,
                quantity = product.quantity,
                price = null // if in the future the api will prvide price we have it ready
            )
            cartItemDao.insertCartItem(cartItem)
        }
    }

    //fun to remove items from cart or products
    fun removeFromCart(cartItem: CartItem) {
        viewModelScope.launch {
            // Remove from cart
            cartItemDao.deleteCartItem(cartItem)

            // Find the corresponding item in the counter and remove it
            val matchingCounterItem = _counterItems.value.find { it.productName == cartItem.productName }
            if (matchingCounterItem != null) {
                removeFromCounter(matchingCounterItem)
            }
        }
    }

    // Favorite operations
    fun addToFavorites(product: Product) {
        viewModelScope.launch {
            val favoriteItem = FavoriteItem(
                productName = product.product_name,
                imageUrl = product.image_url,
                quantity = product.quantity,
                price = null,
                kcal = product.nutriments?.energy_kcal_value,
                fat = product.nutriments?.fat_100g,
                sugar = product.nutriments?.sugars_100g,
                protein = product.nutriments?.proteins_value
            )
            favoriteItemDao.insertFavoriteItem(favoriteItem)
        }
    }
    // fun to delete from favorites
    fun removeFromFavorites(favoriteItem: FavoriteItem) {
        viewModelScope.launch {
            favoriteItemDao.deleteFavoriteItem(favoriteItem)
        }
    }

    // Counter operations
    fun addToCounter(product: Product) {
        viewModelScope.launch {
            val counterItem = CounterItem(
                productName = product.product_name,
                imageUrl = product.image_url,
                kcal = product.nutriments?.energy_kcal_value,
                fat = product.nutriments?.fat_100g,
                sugar = product.nutriments?.sugars_100g,
                protein = product.nutriments?.proteins_value
            )
            counterItemDao.insertCounterItem(counterItem)
        }
    }

    //fun to remove from counter
    fun removeFromCounter(counterItem: CounterItem) {
        viewModelScope.launch {
            counterItemDao.deleteCounterItem(counterItem)
        }
    }

    // Fetch products by barcode from the API
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
