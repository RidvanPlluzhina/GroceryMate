package it.unibz.engineeringofmobilesystems.grocerymateapp.model.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.CartItem
import kotlinx.coroutines.flow.Flow

// Declares the DAO for the CartItem entity.
@Dao
interface CartItemDao {

    // Inserts a single CartItem into the database.
    @Insert
    suspend fun insertCartItem(cartItem: CartItem)

    // Deletes a specific CartItem from the database.
    @Delete
    suspend fun deleteCartItem(cartItem: CartItem)

    // Retrieves all CartItem entries from the database.
    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): Flow<List<CartItem>>
}


