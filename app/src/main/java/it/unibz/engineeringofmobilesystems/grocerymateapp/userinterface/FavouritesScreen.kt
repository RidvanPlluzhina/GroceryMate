package it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import it.unibz.engineeringofmobilesystems.grocerymateapp.R
import it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel.ProductViewModel

@Composable
fun FavouritesScreen(viewModel: ProductViewModel, navController: NavController) {
    val favoritesItems = viewModel.favoritesItems.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        if (favoritesItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Your favorites list is empty",
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
                items(favoritesItems) { product ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .border(1.dp, Color.Gray)
                            .padding(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Product Image
                            AsyncImage(
                                model = product.image_url,
                                contentDescription = product.product_name,
                                modifier = Modifier
                                    .size(140.dp)
                                    .padding(end = 16.dp),
                                contentScale = ContentScale.Fit
                            )

                            // Product Name
                            Text(
                                text = product.product_name,
                                fontSize = 20.sp,
                                modifier = Modifier.weight(1f) // Push the "Remove" icon to the end
                            )

                            // Remove Icon
                            androidx.compose.material3.Icon(
                                painter = painterResource(id = R.drawable.remove),
                                contentDescription = "Remove Item",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        viewModel.removeFromFavourites(product)
                                    },
                                tint = Color.Red
                            )
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


