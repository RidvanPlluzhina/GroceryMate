package it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.products

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel.ProductViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.BottomNavigationBar
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.ProductDetails

@Composable
fun DrinksScreen(viewModel: ProductViewModel = viewModel(), navController: NavController) {

    // Observes the products flow from the viewModel and converts it into a state.
    val products by viewModel.products.collectAsState()

    // Executes a one-time side effect when the screen is loaded.
    LaunchedEffect(Unit) { // Ensures this block runs only once when the composable is first added to the composition.
        viewModel.fetchProductsByBarcodes( // fetches product data for a predefined list of barcodes.
            listOf( "3268840001008","3274080005003","7613035833272","8002270014901", "3179732333919","5060337501316"
                , "9002490205973", "90162800","3502110000651","54491069","5449000006004",
                "40822938","3502110006240","3564700716519","5000436882763","3560070478637")
        )
    }
    Box(modifier = Modifier
        .fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp)
        ) {
            // Iterates over the products list and displays each product.
            items(products) { product ->
                ProductDetails(product, viewModel)
                Spacer(modifier = Modifier.height(30.dp))
            }
        }

        // Bottom Navigation Bar
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(Color(0xFF003DA5))
        ) {
            BottomNavigationBar(navController = navController)
        }
    }
}


