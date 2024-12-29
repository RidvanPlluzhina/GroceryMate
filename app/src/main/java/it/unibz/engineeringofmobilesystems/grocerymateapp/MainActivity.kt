package it.unibz.engineeringofmobilesystems.grocerymateapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.BioProductScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.CartScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.CounterScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.DrinksScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.FavouritesScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.HomeScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.MilkProductScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.SnackProductScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.SweetsScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel.ProductViewModel


class MainActivity : ComponentActivity() {
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
                composable("snacks") {SnackProductScreen(viewModel, navController)}
                composable("cart") { CartScreen(viewModel, navController) }
                composable("favourites") { FavouritesScreen(viewModel, navController) }
                composable("counter") { CounterScreen(viewModel, navController) }

            }
        }
    }
}

