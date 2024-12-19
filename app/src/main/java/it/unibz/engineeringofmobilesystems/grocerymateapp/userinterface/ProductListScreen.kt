package it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.Product
import it.unibz.engineeringofmobilesystems.grocerymateapp.R

@Composable
fun ProductDetails(product: Product) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(250.dp)
            .background(Color.White)
            .border(width = 2.dp, color = Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = product.image_url,
                contentDescription = product.product_name,
                modifier = Modifier
                    .size(150.dp) // Increased size for the bottle
                    .padding(bottom = 18.dp),
                contentScale = ContentScale.Fit
            )
            Text(text = "Name: ${product.product_name}")
            Text(text = "Quantity: ${product.quantity}")
            Text(text = "Brand: ${product.brands}")

            Row(
                modifier = Modifier.padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.trolley),
//                    contentDescription = "Add to Cart",
//                    modifier = Modifier.size(20.dp)
//                )
//                Spacer(modifier = Modifier.width(50.dp))
//                Image(
//                    painter = painterResource(id = R.drawable.heart),
//                    contentDescription = "Add to Favorites",
//                    modifier = Modifier.size(20.dp)
//                )
            }
        }
    }
}


