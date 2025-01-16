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

    // Three mutable state variables to hold user input:
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var description2 by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(150.dp)
            )
            //Review field with slider here declared
            Text(
                text = "Choose a Number (1-5): $description2",
                modifier = Modifier.padding(vertical = 8.dp)
            )
            // UI component which allows us to select a numeric value by sliding the thumb in a range.
            Slider(
                value = description2.toFloat(),
                onValueChange = { description2 = it.toInt() },
                valueRange = 1f..5f,
                // 3 intermediate steps
                steps = 3,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Button(
                onClick = {
                    val review = Review(
                        title = title,
                        description = description,
                        description2 = description2,
                        dateAdded = System.currentTimeMillis()
                    )
                    viewModel.addReview(review)
                    navController.popBackStack()
                },
                modifier = Modifier.align(Alignment.End),
                enabled = title.isNotBlank() && description.isNotBlank()
            ) {
                Text("Publish")
            }

            // Add Spacer to balance the layout below the content
            Spacer(modifier = Modifier.weight(1f))
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


