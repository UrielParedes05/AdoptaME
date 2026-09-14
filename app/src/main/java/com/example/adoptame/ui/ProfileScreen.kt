package com.example.adoptame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoptame.ui.theme.AdoptaMETheme
import com.example.adoptame.ui.theme.BackgroundCream
import com.example.adoptame.ui.theme.PrimaryCoral
import com.example.adoptame.ui.theme.SecondaryGray

@Composable
fun ProfileScreen(onLogout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundCream)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        
        // User Info Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Gray, CircleShape)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = "Sofia Silva", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text(text = "sofia.silva@email.com", fontSize = 14.sp, color = SecondaryGray)
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Profile Menu Options
        Text(text = "Cuenta", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = SecondaryGray)
        Spacer(modifier = Modifier.height(12.dp))
        
        ProfileMenuItem(icon = Icons.Default.Pets, title = "Mis Publicaciones")
        ProfileMenuItem(icon = Icons.Default.Settings, title = "Configuración")
        
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "General", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = SecondaryGray)
        Spacer(modifier = Modifier.height(12.dp))
        
        ProfileMenuItem(icon = Icons.Default.Person, title = "Editar Perfil")

        Spacer(modifier = Modifier.weight(1f))

        // Logout Button
        Button(
            onClick = onLogout,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFE8E3)),
            shape = RoundedCornerShape(28.dp)
        ) {
            Icon(Icons.Default.ExitToApp, contentDescription = null, tint = PrimaryCoral)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Cerrar Sesión", color = PrimaryCoral, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ProfileMenuItem(icon: ImageVector, title: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = PrimaryCoral)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = SecondaryGray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    AdoptaMETheme {
        ProfileScreen(onLogout = {})
    }
}
