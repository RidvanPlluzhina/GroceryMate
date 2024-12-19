package it.unibz.engineeringofmobilesystems.grocerymateapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.DrinksScreen
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.HomeScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController: NavHostController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeScreen(navController) }
                composable("drinks") { DrinksScreen() }
            }
        }
    }
}

