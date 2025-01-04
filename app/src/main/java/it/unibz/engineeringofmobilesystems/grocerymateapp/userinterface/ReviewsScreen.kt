package it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.Review
import it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel.ReviewViewModel

@Composable
fun ReviewsScreen(navController: NavController, viewModel: ReviewViewModel) {
    val reviews by viewModel.reviews.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Button(
                onClick = { navController.navigate("add_review") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF003DA5)) // Custom blue color
            ) {
                Text(
                    text = "Add Review",
                    color = Color.White
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp)
            ) {
                items(reviews.size) { index ->
                    val review = reviews[index]
                    Spacer(modifier = Modifier.height(12.dp))
                    ReviewItem(review = review, onRemove = { viewModel.deleteReview(review) })
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
        }
        IconButton(
            onClick = onRemove,
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
        }
    }
}

