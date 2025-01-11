package it.unibz.engineeringofmobilesystems.grocerymateapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counter_table")
data class CounterItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productName: String,
    val imageUrl: String?,
    val kcal: Float?,
    val fat: Float?,
    val sugar: Float?,
    val protein: Float?
)
