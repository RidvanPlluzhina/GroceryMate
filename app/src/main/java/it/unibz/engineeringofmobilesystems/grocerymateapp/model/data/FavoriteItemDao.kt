package it.unibz.engineeringofmobilesystems.grocerymateapp.model.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.FavoriteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteItemDao {
    @Insert
    suspend fun insertFavoriteItem(favoriteItem: FavoriteItem)

    @Delete
    suspend fun deleteFavoriteItem(favoriteItem: FavoriteItem)

    @Query("SELECT * FROM favorite_items")
    fun getAllFavoriteItems(): Flow<List<FavoriteItem>>
}
