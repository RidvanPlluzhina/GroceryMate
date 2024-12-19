package it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to your Grocery Mate")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("drinks") }) {
            Text("Drinks")

        }
    }
}

