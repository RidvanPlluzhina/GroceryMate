package it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.products

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.lifecycle.viewmodel.compose.viewModel
import it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel.ProductViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.navigation.NavController
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.BottomNavigationBar
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.ProductDetails


@Composable
fun MilkProductScreen(viewModel: ProductViewModel = viewModel(), navController: NavController) {
    val products by viewModel.products.collectAsState()

    // Fetch products when the screen loads
    LaunchedEffect(Unit) {
        viewModel.fetchProductsByBarcodes(
            listOf("8002670500516","8002580010822","3033490004743","20266394","3596710015870","5010578008068","7610900292790","8034066307317"
            ,"5036589200970","8436547770137","3256223377406")
        )
    }

    // Wrap everything in a Box for proper alignment
    Box(modifier = Modifier.fillMaxSize()) {
        // Content of the screen
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp)
        ) {
            items(products) { product ->
                ProductDetails(product, viewModel)
                Spacer(modifier = Modifier.height(16.dp))
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
