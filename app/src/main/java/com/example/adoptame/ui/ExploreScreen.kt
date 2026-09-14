package com.example.adoptame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoptame.models.Pet
import com.example.adoptame.ui.theme.*

@Composable
fun ExploreScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Todos") }
    val categories = listOf("Todos", "Perros", "Gatos", "Aves", "Conejos")

    val allPets = remember {
        listOf(
            Pet(3, "Milo", "Beagle Cachorro", "42", "", "", category = "Perros"),
            Pet(4, "Bella", "Persa Blanco", "28", "", "", category = "Gatos"),
            Pet(5, "Coco", "Canario Amarillo", "15", "", "", category = "Aves"),
            Pet(6, "Toby", "Angora Turco", "18", "", "", category = "Gatos"),
            Pet(7, "Lola", "Conejo Cabeza de León", "33", "", "", category = "Conejos"),
            Pet(8, "Rocky", "Pastor Alemán", "51", "", "", category = "Perros")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundCream)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Explorar", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Box(modifier = Modifier.size(40.dp).background(Color.Gray, CircleShape))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Buscar mascotas...", color = SecondaryGray.copy(alpha = 0.5f)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = SecondaryGray) },
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = PrimaryCoral,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Categories
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(categories) { category ->
                CategoryChip(category, selectedCategory == category) { selectedCategory = category }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(allPets) { pet ->
                PetExploreCard(pet)
            }
        }
    }
}

@Composable
fun PetExploreCard(pet: Pet) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Box {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                )
                Surface(
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.align(Alignment.TopEnd).padding(8.dp).size(28.dp)
                ) {
                    Icon(Icons.Outlined.FavoriteBorder, contentDescription = null, modifier = Modifier.padding(6.dp), tint = PrimaryCoral)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(pet.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(pet.age, color = SecondaryGray, fontSize = 12.sp)
            }
            Text(pet.breed, color = SecondaryGray, fontSize = 12.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExplorePreview() {
    AdoptaMETheme {
        ExploreScreen()
    }
}
