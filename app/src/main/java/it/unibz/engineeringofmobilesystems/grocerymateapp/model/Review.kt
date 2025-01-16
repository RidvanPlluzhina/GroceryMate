package it.unibz.engineeringofmobilesystems.grocerymateapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// table declaration where the data will be saved

@Entity(tableName = "review_table")
data class Review(
    val title: String,
    val description: String,
    val description2: Int,
    val dateAdded: Long,
    @PrimaryKey(autoGenerate = true) val id: Int = 0 // Defines a unique identifier (id) for each review.
)

