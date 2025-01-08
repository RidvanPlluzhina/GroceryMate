package it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import it.unibz.engineeringofmobilesystems.grocerymateapp.R
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.BottomNavigationBar
import it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel.ProductViewModel


@Composable
fun CartScreen(viewModel: ProductViewModel, navController: NavController) {
    val cartItems = viewModel.cartItems.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Your cart is empty",
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
                items(cartItems) { cartItem ->
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
                                model = cartItem.imageUrl,
                                contentDescription = cartItem.productName,
                                modifier = Modifier
                                    .size(140.dp)
                                    .padding(end = 16.dp),
                                contentScale = ContentScale.Fit
                            )

                            // Product Name
                            Text(
                                text = cartItem.productName,
                                fontSize = 20.sp,
                                modifier = Modifier.weight(1f)
                            )

                            // Remove Icon
                            Icon(
                                painter = painterResource(id = R.drawable.remove),
                                contentDescription = "Remove Item",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        viewModel.removeFromCart(cartItem)
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

