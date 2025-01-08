package it.unibz.engineeringofmobilesystems.grocerymateapp.model.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartItemDao {
    @Insert
    suspend fun insertCartItem(cartItem: CartItem)

    @Delete
    suspend fun deleteCartItem(cartItem: CartItem)

    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): Flow<List<CartItem>>
}
