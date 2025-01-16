package it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.reviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.unibz.engineeringofmobilesystems.grocerymateapp.R
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.Review
import it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel.ReviewViewModel

// Composable fun for the whole screen containing nav rail, button and review item.
@Composable
fun ReviewsScreen(navController: NavController, viewModel: ReviewViewModel) {
    val reviews by viewModel.reviews.collectAsState()

    Row(modifier = Modifier.fillMaxSize()) {

        NavigationRail(
            containerColor = Color(0xFF003DA5),
            modifier = Modifier.width(90.dp),
            content = {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Navigation Items
                    NavigationRailItem(
                        icon = { Icon(painterResource(id = R.drawable.home), contentDescription = "Home",
                            tint = Color.White, modifier = Modifier.size(40.dp)) },
                        label = { Text("Home", fontSize = 15.sp, color = Color.White) },
                        selected = false,
                        onClick = { navController.navigate("home") }
                    )
                    NavigationRailItem(
                        icon = { Icon(painterResource(id = R.drawable.cart1), contentDescription = "Cart",
                            tint = Color.White, modifier = Modifier.size(40.dp)) },
                        label = { Text("Cart", fontSize = 15.sp, color = Color.White) },
                        selected = false,
                        onClick = { navController.navigate("cart") }
                    )
                    NavigationRailItem(
                        icon = { Icon(painterResource(id = R.drawable.heart), contentDescription = "Favourites",
                            tint = Color.White, modifier = Modifier.size(40.dp)) },
                        label = { Text("Favourites", fontSize = 15.sp, color = Color.White) },
                        selected = false,
                        onClick = { navController.navigate("favourites") }
                    )
                    NavigationRailItem(
                        icon = { Icon(painterResource(id = R.drawable.track), contentDescription = "Counter",
                            tint = Color.White, modifier = Modifier.size(40.dp)) },
                        label = { Text("Counter", fontSize = 15.sp, color = Color.White) },
                        selected = false,
                        onClick = { navController.navigate("counter") }
                    )
                }
            }
        )


        // Here is the Main Content
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Button(
                    onClick = { navController.navigate("add_review") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF003DA5))
                ) {
                    Text(
                        text = "Add Review",
                        color = Color.White
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp)
                ) {
                    items(reviews.size) { index ->
                        val review = reviews[index]
                        Spacer(modifier = Modifier.height(12.dp))
                        ReviewItem(review = review, onRemove = { viewModel.deleteReview(review) })
                    }
                }
            }
        }
    }
}
// composable fun where the review is placed.
@Composable
fun ReviewItem(review: Review, onRemove: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFB2DFB5))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 40.dp)
        ) {
            Text(
                text = "Title: ${review.title}",
                fontSize = 25.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = review.description,
                fontSize = 20.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Review (0/5): ${review.description2}",
                fontSize = 20.sp,
                color = Color.Black
            )
        }
        IconButton(
            onClick = onRemove,
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
        }
    }
}
