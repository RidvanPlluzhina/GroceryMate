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
fun MilkProductScreen(viewModel: ProductViewModel = viewModel(), navController: NavController) {
    val products by viewModel.products.collectAsState()

    // Fetch products when the screen loads
    LaunchedEffect(Unit) {
        viewModel.fetchProductsByBarcodes(
            listOf(
                "9300633765705",
                "5701638142081",
                "8850393801096",
                "0078742352015",
                "5287000064019",
                "6001008784712",
                "5411188115670",
                "7613035315613",
                "3228857000856",
                "9415007060012",
                "742365004119",
                "7394376616115",
                "41570056344",
                "25293600211",
                "8425324000216",
                "744473912002",
                "018944000060",
                "5060107120012",
                "7310861000013",
                "6408432000012"
            )
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
