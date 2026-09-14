package com.example.adoptame.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoptame.models.Pet
import com.example.adoptame.ui.theme.*

@Composable
fun HomeScreen() {
    var selectedCategory by remember { mutableStateOf("Perros") }
    val categories = listOf("Perros", "Gatos", "Otros")
    
    // Fake Data
    val pets = remember {
        listOf(
            Pet(
                id = 1,
                name = "Max",
                breed = "Golden Retriever (Cachorro)",
                age = "3 meses",
                description = "Es un cachorro sumamente juguetón, inteligente y cariñoso. Le encanta correr en el jardín y ya sa...",
                publisherName = "Carlos Mendoza",
                category = "Perros"
            ),
            Pet(
                id = 2,
                name = "Luna",
                breed = "Siamesa Mezcla",
                age = "2 meses",
                description = "Luna es súper dulce y tranquila. Le fascina acurrucarse en tu regazo mientras ronronea. Est...",
                publisherName = "Gabriela Torres",
                category = "Gatos"
            )
        )
    }

    Scaffold(
        bottomBar = { PatitasBottomNavBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = PrimaryCoral,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Publicar")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundCream)
                .padding(padding)
        ) {
            PatitasTopBar()
            
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Text(
                    text = "¡Hola, Sofia! 👋",
                    fontSize = 16.sp,
                    color = SecondaryGray
                )
                Text(
                    text = "Encuentra tu compañero ideal",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    items(categories) { category ->
                        CategoryChip(
                            name = category,
                            isSelected = selectedCategory == category,
                            onClick = { selectedCategory = category }
                        )
                    }
                }
            }

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(pets) { pet ->
                    PetCard(pet)
                }
            }
        }
    }
}

@Composable
fun PatitasTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color(0xFFFFE8E3), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("🐾", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Patitas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryCoral
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { }) {
                Icon(Icons.Outlined.Notifications, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Gray, CircleShape)
            )
        }
    }
}

@Composable
fun CategoryChip(name: String, isSelected: Boolean, onClick: () -> Unit) {
    val icon = when(name) {
        "Perros" -> "🐶"
        "Gatos" -> "🐱"
        else -> "🐰"
    }
    
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) PrimaryCoral else Color.White,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(icon)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = name,
                color = if (isSelected) Color.White else SecondaryGray,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun PetCard(pet: Pet) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box {
                // Image Placeholder
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.LightGray)
                ) {
                    Text("Imagen de ${pet.name}", Modifier.align(Alignment.Center))
                }
                
                Surface(
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .size(36.dp)
                ) {
                    Icon(
                        Icons.Outlined.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp),
                        tint = PrimaryCoral
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = pet.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Surface(
                    color = AgeTagColor,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = pet.age,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        color = PrimaryCoral,
                        fontSize = 12.sp
                    )
                }
            }
            
            Text(text = pet.breed, color = SecondaryGray, fontSize = 14.sp)
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = pet.description,
                color = SecondaryGray,
                fontSize = 14.sp,
                maxLines = 2
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(32.dp).background(Color.Gray, CircleShape))
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("Publicado por", fontSize = 10.sp, color = SecondaryGray)
                    Text(pet.publisherName, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Chat, contentDescription = null, Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Contactar por WhatsApp", fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun PatitasBottomNavBar() {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Inicio", fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = PrimaryCoral, selectedTextColor = PrimaryCoral)
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.Search, contentDescription = null) },
            label = { Text("Explorar", fontSize = 10.sp) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.AddBox, contentDescription = null) },
            label = { Text("Publicar", fontSize = 10.sp) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Outlined.FavoriteBorder, contentDescription = null) },
            label = { Text("Favoritos", fontSize = 10.sp) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Outlined.Person, contentDescription = null) },
            label = { Text("Perfil", fontSize = 10.sp) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    AdoptaMETheme {
        HomeScreen()
    }
}
