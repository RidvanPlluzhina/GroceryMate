package it.unibz.engineeringofmobilesystems.grocerymateapp.model.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.CartItem
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.CounterItem
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.FavoriteItem
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.Review

@Database(
    entities = [Review::class, CartItem::class, FavoriteItem::class, CounterItem::class],
    version = 5, // Increment version for schema changes
    exportSchema = false
)
abstract class ReviewDatabase : RoomDatabase() {
    abstract fun reviewDao(): ReviewDao
    abstract fun cartItemDao(): CartItemDao
    abstract fun favoriteItemDao(): FavoriteItemDao
    abstract fun counterItemDao(): CounterItemDao

    companion object {
        @Volatile
        private var INSTANCE: ReviewDatabase? = null

        fun getInstance(context: Context): ReviewDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReviewDatabase::class.java,
                    "review_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
