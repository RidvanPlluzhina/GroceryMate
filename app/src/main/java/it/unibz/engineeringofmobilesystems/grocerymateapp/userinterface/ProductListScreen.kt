package it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.Product
import it.unibz.engineeringofmobilesystems.grocerymateapp.R
import it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel.ProductViewModel


// composable function which holds the details for each fetched product
@Composable
fun ProductDetails(product: Product, viewModel: ProductViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(300.dp)
            .background(Color.White)
            .border(width = 4.dp, color = Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "Add to Cart",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            viewModel.addToCart(product)
                            viewModel.addToCounter(product)
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = "Add to Favorites",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            viewModel.addToFavorites(product)
                        }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            AsyncImage(
                model = product.image_url,
                contentDescription = product.product_name,
                modifier = Modifier
                    .size(170.dp)
                    .padding(bottom = 18.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = "${product.product_name}",
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                fontSize = 25.sp
            )
            Text(
                text = "Quantity: ${product.quantity}",
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}





