package it.unibz.engineeringofmobilesystems.grocerymateapp.model.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.Review
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
