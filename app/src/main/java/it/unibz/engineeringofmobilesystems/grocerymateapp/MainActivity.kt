package it.unibz.engineeringofmobilesystems.grocerymateapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController: NavHostController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeScreen(navController) }
                composable("drinks") { DrinksScreen() }
                composable("bio products") { BioProductScreen() }
                composable("milk products") { MilkProductScreen() }
                composable("sweets") { SweetsScreen() }
                composable("snacks") {SnackProductScreen()}
                composable("cart") { CartScreen() }
                composable("favourites") { FavouritesScreen() }
                composable("counter") { CounterScreen() }



            }
        }
    }
}

