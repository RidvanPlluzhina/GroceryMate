package it.unibz.engineeringofmobilesystems.grocerymateapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Primary key
    val productName: String,
    val imageUrl: String?,
    val quantity: String?,
    val price: Double?
)
