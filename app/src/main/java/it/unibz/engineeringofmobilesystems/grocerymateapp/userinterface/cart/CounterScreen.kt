package it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.BottomNavigationBar
import it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel.ProductViewModel

@Composable
fun CounterScreen(viewModel: ProductViewModel, navController: NavController) {
    val counterItems = viewModel.counterItems.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        if (counterItems.isEmpty()) {
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
                items(counterItems) { item ->
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
                                model = item.imageUrl,
                                contentDescription = item.productName,
                                modifier = Modifier
                                    .size(150.dp)
                                    .padding(end = 16.dp),
                                contentScale = ContentScale.Fit
                            )

                            // Column for Product Details
                            Column {
                                Text(
                                    text = item.productName,
                                    fontSize = 20.sp,
                                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                                )
                                Text(
                                    text = "Kcal: ${item.kcal ?: "Not Available"}",
                                    fontSize = 16.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "Fat: ${item.fat ?: "Not Available"}",
                                    fontSize = 16.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "Sugar: ${item.sugar ?: "Not Available"}",
                                    fontSize = 16.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "Protein: ${item.protein ?: "Not Available"}",
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
