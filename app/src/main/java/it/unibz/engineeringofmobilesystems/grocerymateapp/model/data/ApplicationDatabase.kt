package it.unibz.engineeringofmobilesystems.grocerymateapp.model.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.CartItem
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.CounterItem
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.FavoriteItem
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.Review

// Marks this class as a Room database and provides metadata.
@Database(
    entities = [Review::class, CartItem::class, FavoriteItem::class, CounterItem::class],
    version = 7, // Increment version for schema changes
    exportSchema = false
)
// Defines the database class extending RoomDatabase.
abstract class ApplicationDatabase : RoomDatabase() {

    // Provides access to the DAOs for each entity.
    abstract fun reviewDao(): ReviewDao
    abstract fun cartItemDao(): CartItemDao
    abstract fun favoriteItemDao(): FavoriteItemDao
    abstract fun counterItemDao(): CounterItemDao

    // Ensure a single database instance throughout the app's lifecycle.
    companion object {
        @Volatile // Ensures visibility of changes to INSTANCE across threads.
        private var INSTANCE: ApplicationDatabase? = null

        // Method
        fun getInstance(context: Context): ApplicationDatabase {
            return INSTANCE ?: synchronized(this) { // To ensure only one thread initializes the database.
                val instance = Room.databaseBuilder( // Creates a database with the name "application_database"
                    context.applicationContext,
                    ApplicationDatabase::class.java,
                    "application_database"
                )
                    .fallbackToDestructiveMigration() // Drops and recreates the database if there’s a schema version mismatch.
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
