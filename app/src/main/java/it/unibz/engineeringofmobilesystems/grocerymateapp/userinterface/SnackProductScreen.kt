package it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface

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


@Composable
fun SnackProductScreen(viewModel: ProductViewModel = viewModel(), navController: NavController) {
    val products by viewModel.products.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchProductsByBarcodes(
            listOf( "7613035833272","8002270014901", "3179732333919", "9002490205973", "90162800"
                ,"3502110000651","54491069","5449000286291")
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
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



