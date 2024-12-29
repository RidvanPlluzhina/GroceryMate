package it.unibz.engineeringofmobilesystems.grocerymateapp.userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.unibz.engineeringofmobilesystems.grocerymateapp.R

@Composable
fun HomeScreen(navController: NavController) {
    val categories = listOf(
        Pair("Drinks", R.drawable.drinks1),
        Pair("Snacks", R.drawable.snacks121),
        Pair("Sweets", R.drawable.sweets),
        Pair("Milk Products", R.drawable.milk),
        Pair("Bio Products", R.drawable.bio),
        )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            HeaderBar(title = "YOUR GROCERY MATE")

            categories.forEach { category ->
                CategoryCard(
                    name = category.first,
                    imageRes = category.second,
                    onClick = { navController.navigate(category.first.lowercase()) }
                )
            }
        }

        // Bottom Navigation Bar placed at the bottom
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
fun HeaderBar(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF003DA5))
            .padding(25.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Title
        Text(
            text = title,
            color = Color.White,
            fontSize = 25.sp
        )

        // Location Icon
        Icon(
            painter = painterResource(id = R.drawable.location),
            contentDescription = "Location Icon",
            tint = Color.White,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BottomNavigationItem(
            iconRes = R.drawable.home,
            label = "Home",
            onClick = { navController.navigate("home") }
        )
        BottomNavigationItem(
            iconRes = R.drawable.cart,
            label = "Cart",
            onClick = { navController.navigate("cart") }
        )
        BottomNavigationItem(
            iconRes = R.drawable.heart,
            label = "Favourites",
            onClick = { navController.navigate("favourites") }
        )
        BottomNavigationItem(
            iconRes = R.drawable.counter,
            label = "Counter",
            onClick = { navController.navigate("counter") }
        )
    }
}

@Composable
fun BottomNavigationItem(
    iconRes: Int,
    label: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = Color.White,
            fontSize = 15.sp
        )
    }
}

@Composable
fun CategoryCard(name: String, imageRes: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(Color.White)
            .height(125.dp)
            .border(3.dp, Color.Black, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = name,
            modifier = Modifier
                .size(200.dp)
                .padding(end = 10.dp)
        )
        Text(
            text = name,
            fontSize = 25.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = Color.Black
        )
    }
}
