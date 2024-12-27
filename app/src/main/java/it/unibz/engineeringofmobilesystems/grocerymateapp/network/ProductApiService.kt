package it.unibz.engineeringofmobilesystems.grocerymateapp.network

import it.unibz.engineeringofmobilesystems.grocerymateapp.model.FoodResponse
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.Product
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// the link to check the json file for each product based on their barcode
// https://world.openfoodfacts.org/api/v0/product/{write barcode here}.json

private const val BASE_URL = "https://world.openfoodfacts.org/"

interface ProductApiService {
    @GET("api/v0/product/{barcode}.json")
    suspend fun getProductByBarcode(@Path("barcode") barcode: String): ProductWrapper
}

data class ProductWrapper(
    val product: Product
)

object RetrofitInstance {
    val api: ProductApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApiService::class.java)
    }
}
