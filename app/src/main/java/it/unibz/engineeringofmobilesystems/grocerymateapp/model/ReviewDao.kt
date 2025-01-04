package it.unibz.engineeringofmobilesystems.grocerymateapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {
    @Upsert
    suspend fun upsert(review: Review)

    @Delete
    suspend fun delete(review: Review)

    @Query("SELECT * FROM review_table ORDER BY dateAdded DESC")
    fun getAllReviews(): Flow<List<Review>>
}
