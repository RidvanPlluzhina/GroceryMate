package it.unibz.engineeringofmobilesystems.grocerymateapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.data.ReviewDatabase
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

class MainActivity : ComponentActivity() {

    private val reviewDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            ReviewDatabase::class.java,
            "review_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    private val reviewViewModel by lazy {
        ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                    return ReviewViewModel(reviewDatabase.reviewDao()) as T
                }
            }
        )[ReviewViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController: NavHostController = rememberNavController()
            val viewModel: ProductViewModel = viewModel()

            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeScreen(navController) }
                composable("drinks") { DrinksScreen(viewModel, navController) }
                composable("bio products") { BioProductScreen(viewModel, navController) }
                composable("milk products") { MilkProductScreen(viewModel, navController) }
                composable("sweets") { SweetsScreen(viewModel, navController) }
                composable("snacks") { SnackProductScreen(viewModel, navController) }
                composable("cart") { CartScreen(viewModel, navController) }
                composable("favourites") { FavouritesScreen(viewModel, navController) }
                composable("counter") { CounterScreen(viewModel, navController) }
                composable("reviews") {
                    ReviewsScreen(navController = navController, viewModel = reviewViewModel)
                }
                composable("add_review") {
                    AddReviewScreen(navController = navController, viewModel = reviewViewModel)
                }
            }
        }
    }
}
