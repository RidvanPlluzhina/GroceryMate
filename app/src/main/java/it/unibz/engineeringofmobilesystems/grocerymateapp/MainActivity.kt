// package declaration
package it.unibz.engineeringofmobilesystems.grocerymateapp

// imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.data.ApplicationDatabase
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.*
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.cart.CartScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.cart.CounterScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.cart.FavouritesScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.products.BioProductScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.products.DrinksScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.products.MilkProductScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.products.SnackProductScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.products.SweetsScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.reviews.AddReviewScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.reviews.ReviewsScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel.ReviewViewModel
import it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel.ProductViewModel

// Declares the MainActivity class which inherits from ComponentActivity
class MainActivity : ComponentActivity() {

    // Lazily initializes the Room database instance for the application
    private val applicationDatabase by lazy {

        Room.databaseBuilder(
            applicationContext,
            ApplicationDatabase::class.java, // Specifies the database class
            "application_database" // Name of the database file
        )
            .fallbackToDestructiveMigration() // Ensures schema changes do not crash the app
            .build() // Builds the database instance
    }
    // Lazily initializes the ReviewViewModel, providing the ReviewDao for database operations
    private val reviewViewModel by lazy {

        ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                    // Returns a new instance of ReviewViewModel with the DAO
                    return ReviewViewModel(applicationDatabase.reviewDao()) as T
                }
            }
        )[ReviewViewModel::class.java] // Specifies the ViewModel class
    }

    // Lazily initializes the ProductViewModel, passing in multiple DAOs for various entities
    private val productViewModel by lazy {
        ViewModelProvider(
            this, // Lifecycle owner is the current activity
            object : ViewModelProvider.Factory { // Custom factory for creating the ViewModel
                override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                    // Returns a new instance of ProductViewModel with the required DAOs
                    return ProductViewModel(
                        cartItemDao = applicationDatabase.cartItemDao(),
                        favoriteItemDao = applicationDatabase.favoriteItemDao(),
                        counterItemDao = applicationDatabase.counterItemDao()
                    ) as T
                }
            }
        )[ProductViewModel::class.java] // Specifies the ViewModel class
    }

    // Overriding the onCreate method to set up the activity's content and navigation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Calls the parent class's onCreate method
        setContent {
            // Sets up the composable content for the activity
            val navController: NavHostController = rememberNavController() // Creates and remembers a navigation controller

            // Sets up a navigation host with a starting destination "home"
            NavHost(navController = navController, startDestination = "home") {
                // Defines the "home" route and its associated composable content
                composable("home") { HomeScreen(navController) }
                // Defines the "drinks" route, passing ProductViewModel and navController, same for others
                composable("drinks") { DrinksScreen(productViewModel, navController) }
                composable("bio products") { BioProductScreen(productViewModel, navController) }
                composable("milk products") { MilkProductScreen(productViewModel, navController) }
                composable("sweets") { SweetsScreen(productViewModel, navController) }
                composable("snacks") { SnackProductScreen(productViewModel, navController) }
                composable("cart") { CartScreen(productViewModel, navController) }
                composable("favourites") { FavouritesScreen(productViewModel, navController) }
                composable("counter") { CounterScreen(productViewModel, navController) }
                composable("reviews") {
                    // Defines the "reviews" route, passing ReviewViewModel and navController
                    ReviewsScreen(navController = navController, viewModel = reviewViewModel)
                }
                composable("add_review") {
                    // Defines the "add_review" route, passing ReviewViewModel and navController
                    AddReviewScreen(navController = navController, viewModel = reviewViewModel)
                }
            }
        }
    }
}
