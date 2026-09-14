package com.example.adoptame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoptame.models.Pet
import com.example.adoptame.ui.theme.AdoptaMETheme
import com.example.adoptame.ui.theme.BackgroundCream

@Composable
fun FavoritesScreen() {
    val favoritePets = remember {
        listOf(
            Pet(1, "Max", "Golden Retriever (Cachorro)", "3 meses", "Es un cachorro sumamente juguetón, inteligente y cariñoso.", "Carlos Mendoza", category = "Perros"),
            Pet(2, "Luna", "Siamesa Mezcla", "2 meses", "Luna es súper dulce y tranquila. Le fascina acurrucarse.", "Gabriela Torres", category = "Gatos")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundCream)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Mis Favoritos",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(favoritePets) { petItem ->
                PetCard(petItem)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesPreview() {
    AdoptaMETheme {
        FavoritesScreen()
    }
}
