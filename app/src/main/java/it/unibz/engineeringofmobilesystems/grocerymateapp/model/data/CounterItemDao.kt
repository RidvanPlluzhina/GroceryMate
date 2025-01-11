package it.unibz.engineeringofmobilesystems.grocerymateapp.model.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.CounterItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CounterItemDao {
    @Insert
    suspend fun insertCounterItem(counterItem: CounterItem)

    @Delete
    suspend fun deleteCounterItem(counterItem: CounterItem)

    @Query("SELECT * FROM counter_table")
    fun getAllCounterItems(): Flow<List<CounterItem>>
}
