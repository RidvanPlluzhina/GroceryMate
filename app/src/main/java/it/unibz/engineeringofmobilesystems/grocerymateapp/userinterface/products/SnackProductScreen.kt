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
fun SnackProductScreen(viewModel: ProductViewModel = viewModel(), navController: NavController) {
    val products by viewModel.products.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchProductsByBarcodes(
            listOf("8480000336408","3168930009184","5053990156061","3175680011480","3229820100234"
            ,"3027030011810","3041090063152","7622210233721","20337292","20054793",
                "3336971609018","8013355999679","8412600028599","3168930000662","3222477003293","3168930011231","3017760790192","8480000340368",
                "4088600277967","0024100440702","4001242108543","5000168006666","5941588006938")

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



