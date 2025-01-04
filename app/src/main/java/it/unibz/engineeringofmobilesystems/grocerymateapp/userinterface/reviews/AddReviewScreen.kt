package it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.reviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.Review
import it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface.BottomNavigationBar
import it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel.ReviewViewModel

@Composable
fun AddReviewScreen(navController: NavController, viewModel: ReviewViewModel) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }


    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth().padding(vertical = 8.dp)
                    .height(150.dp),
            )
            Button(
                onClick = {
                    val review = Review(
                        title = title,
                        description = description,
                        dateAdded = System.currentTimeMillis()
                    )
                    viewModel.addReview(review)
                    navController.popBackStack()
                },
                modifier = Modifier.align(Alignment.End),
                enabled = title.isNotBlank() && description.isNotBlank()
            )
            {
                Text("Publish")
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
