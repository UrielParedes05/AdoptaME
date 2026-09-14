package com.example.adoptame.ui

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoptame.models.Pet
import com.example.adoptame.ui.theme.*
import com.example.adoptame.ui.PublishScreen
import com.example.adoptame.ui.ExploreScreen
import com.example.adoptame.ui.FavoritesScreen
import com.example.adoptame.ui.ProfileScreen

@Composable
fun MainScreen(onLogout: () -> Unit) {
    var selectedTab by remember { mutableStateOf("Inicio") }

    Scaffold(
        bottomBar = { 
            PatitasBottomNavBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            ) 
        },
        floatingActionButton = {
            if (selectedTab == "Inicio") {
                FloatingActionButton(
                    onClick = { selectedTab = "Publicar" },
                    containerColor = PrimaryCoral,
                    contentColor = Color.White,
                    shape = CircleShape
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Publicar")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedTab) {
                "Inicio" -> HomeContent()
                "Explorar" -> ExploreScreen()
                "Publicar" -> PublishScreen(onBack = { selectedTab = "Inicio" })
                "Favoritos" -> FavoritesScreen()
                "Perfil" -> ProfileScreen(onLogout = onLogout)
                else -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Pantalla de $selectedTab en desarrollo")
                    }
                }
            }
        }
    }
}

@Composable
fun HomeContent() {
    var selectedCategory by remember { mutableStateOf("Perros") }
    val categories = listOf("Perros", "Gatos", "Otros")
    
    val pets = remember {
        listOf(
            Pet(1, "Max", "Golden Retriever (Cachorro)", "3 meses", "Es un cachorro sumamente juguetón...", "Carlos Mendoza", category = "Perros"),
            Pet(2, "Luna", "Siamesa Mezcla", "2 meses", "Luna es súper dulce...", "Gabriela Torres", category = "Gatos")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundCream)
    ) {
        PatitasTopBar()
        
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text("¡Hola, Sofia! 👋", fontSize = 16.sp, color = SecondaryGray)
            Text("Encuentra tu compañero ideal", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                items(categories) { category ->
                    CategoryChip(category, selectedCategory == category) { selectedCategory = category }
                }
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(pets) { pet -> PetCard(pet) }
        }
    }
}

@Composable
fun PatitasTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(32.dp).background(Color(0xFFFFE8E3), CircleShape), contentAlignment = Alignment.Center) {
                Text("🐾", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Patitas", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = PrimaryCoral)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { }) { Icon(Icons.Outlined.Notifications, contentDescription = null) }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.size(40.dp).background(Color.Gray, CircleShape))
        }
    }
}

@Composable
fun CategoryChip(name: String, isSelected: Boolean, onClick: () -> Unit) {
    val icon = when(name) {
        "Perros" -> "🐶"
        "Gatos" -> "🐱"
        "Aves" -> "🦜"
        "Conejos" -> "🐰"
        else -> "🐹"
    }
    
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) PrimaryCoral else Color.White,
        shadowElevation = 2.dp
    ) {
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(icon)
            Spacer(modifier = Modifier.width(8.dp))
            Text(name, color = if (isSelected) Color.White else SecondaryGray, fontWeight = FontWeight.Medium)
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
                Box(modifier = Modifier.fillMaxWidth().height(200.dp).clip(RoundedCornerShape(20.dp)).background(Color.LightGray)) {
                    Text("Imagen de ${pet.name}", Modifier.align(Alignment.Center))
                }
                Surface(shape = CircleShape, color = Color.White.copy(alpha = 0.8f), modifier = Modifier.align(Alignment.TopEnd).padding(12.dp).size(36.dp)) {
                    Icon(Icons.Outlined.FavoriteBorder, contentDescription = null, modifier = Modifier.padding(8.dp), tint = PrimaryCoral)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(pet.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Surface(color = AgeTagColor, shape = RoundedCornerShape(12.dp)) {
                    Text(pet.age, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), color = PrimaryCoral, fontSize = 12.sp)
                }
            }
            Text(pet.breed, color = SecondaryGray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(pet.description, color = SecondaryGray, fontSize = 14.sp, maxLines = 2)
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
fun PatitasBottomNavBar(selectedTab: String, onTabSelected: (String) -> Unit) {
    val items = listOf("Inicio", "Explorar", "Publicar", "Favoritos", "Perfil")
    val icons = listOf(Icons.Default.Home, Icons.Default.Search, Icons.Default.AddBox, Icons.Outlined.FavoriteBorder, Icons.Outlined.Person)

    NavigationBar(containerColor = Color.White, tonalElevation = 8.dp) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedTab == item,
                onClick = { onTabSelected(item) },
                icon = { Icon(icons[index], contentDescription = item) },
                label = { Text(item, fontSize = 10.sp) },
                colors = NavigationBarItemDefaults.colors(selectedIconColor = PrimaryCoral, selectedTextColor = PrimaryCoral)
            )
        }
    }
}
