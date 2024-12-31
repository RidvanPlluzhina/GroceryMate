package it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel.ProductViewModel

@Composable
fun CounterScreen(viewModel: ProductViewModel, navController: NavController) {
    val cartItems = viewModel.cartItems.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(3.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Your counter screen is empty",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp)
            ) {
                items(cartItems) { product ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .border(1.dp, Color.Gray)
                            .padding(8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // Product Image
                            AsyncImage(
                                model = product.image_url,
                                contentDescription = product.product_name,
                                modifier = Modifier
                                    .size(150.dp)
                                    .padding(end = 16.dp),
                                contentScale = ContentScale.Fit
                            )

                            // Column for Product Name and Kcal
                            Column {
                                Text(
                                    text = product.product_name,
                                    fontSize = 20.sp,
                                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                                )
                                Text(
                                    text = "Kcal: ${product.nutriments?.energy_kcal_value ?: "Not Available"}",
                                    fontSize = 16.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "Fat: ${product.nutriments?.fat_100g ?: "Not Available"}",
                                    fontSize = 16.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "Sugar: ${product.nutriments?.sugars_100g?: "Not Available"}",
                                    fontSize = 16.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "Protein: ${product.nutriments?.proteins_value ?: "Not Available"}",
                                    fontSize = 16.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
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

