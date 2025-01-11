package it.unibz.engineeringofmobilesystems.grocerymateapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_items")
data class FavoriteItem(
    @PrimaryKey val productName: String,
    val imageUrl: String?,
    val quantity: String?,
    val price: Float?,
    val kcal: Float?,
    val fat: Float?,
    val sugar: Float?,
    val protein: Float?
)
