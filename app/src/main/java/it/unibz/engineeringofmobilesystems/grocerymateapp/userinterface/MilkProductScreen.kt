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

@Composable
fun MilkProductScreen(viewModel: ProductViewModel = viewModel()) {
    val products by viewModel.products.collectAsState()

    // Fetch products when the screen loads
    LaunchedEffect(Unit) {
        viewModel.fetchProductsByBarcodes(
            listOf("20266394", "3033490004743", "3023260030928", "3073781192186", "8003170060104","5411188134985","5201037709774","245413451804",
                "3256223377406","7622210103673","2006050106639") // Cristaline Water and Coca-Cola barcodes
        )
    }

    // Display products in a scrollable list
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(products) { product ->
            ProductDetails(product)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
