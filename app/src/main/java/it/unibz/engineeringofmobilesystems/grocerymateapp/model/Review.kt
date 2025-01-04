package it.unibz.engineeringofmobilesystems.grocerymateapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "review_table")
data class Review(
    val title: String,
    val description: String,
    val dateAdded: Long,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
