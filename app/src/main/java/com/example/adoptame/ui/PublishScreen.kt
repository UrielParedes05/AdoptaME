package com.example.adoptame.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoptame.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PublishScreen(onBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var petType by remember { mutableStateOf("Perro") }
    var age by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var whatsapp by remember { mutableStateOf("+51 987 654 321") }
    var description by remember { mutableStateOf("") }
    
    var expandedDropdown by remember { mutableStateOf(false) }
    val petTypes = listOf("Perro", "Gato", "Ave", "Conejo", "Otros")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundCream)
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White, CircleShape)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.Black)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Publicar Mascota",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Photo Upload Box Visual (Borde simulado)
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF0ED)),
            border = BorderStroke(1.dp, PrimaryCoral), // Borde continuo simple temporal
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    shape = CircleShape,
                    color = Color.White,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        Icons.Default.CameraAlt,
                        contentDescription = null,
                        modifier = Modifier.padding(12.dp),
                        tint = PrimaryCoral
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Agregar foto de la mascota",
                    color = PrimaryCoral,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Form Fields
        PublishInputField(label = "Nombre de la mascota", value = name, onValueChange = { name = it }, placeholder = "Ej. Max, Luna, Bobby...")
        
        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown Field
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Tipo de mascota", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
            ExposedDropdownMenuBox(
                expanded = expandedDropdown,
                onExpandedChange = { expandedDropdown = !expandedDropdown }
            ) {
                OutlinedTextField(
                    value = petType,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                    modifier = Modifier.fillMaxWidth().menuAnchor(),
                    shape = RoundedCornerShape(24.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFE0E0E0),
                        focusedBorderColor = PrimaryCoral,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    )
                )
                ExposedDropdownMenu(
                    expanded = expandedDropdown,
                    onDismissRequest = { expandedDropdown = false }
                ) {
                    petTypes.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type) },
                            onClick = {
                                petType = type
                                expandedDropdown = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        PublishInputField(label = "Edad", value = age, onValueChange = { age = it }, placeholder = "Ej. 3 meses, 2 años...")
        
        Spacer(modifier = Modifier.height(16.dp))
        PublishInputField(label = "Ubicación", value = location, onValueChange = { location = it }, placeholder = "Ej. Miraflores, Lima...")

        Spacer(modifier = Modifier.height(16.dp))
        // WhatsApp Field
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Número de WhatsApp", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
            OutlinedTextField(
                value = whatsapp,
                onValueChange = { whatsapp = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { 
                    Surface(shape = CircleShape, color = WhatsAppGreen, modifier = Modifier.padding(8.dp).size(24.dp)) {
                        Icon(Icons.Default.Phone, contentDescription = null, tint = Color.White, modifier = Modifier.padding(4.dp))
                    }
                },
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    focusedBorderColor = PrimaryCoral,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        // Description
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Descripción de la mascota", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier.fillMaxWidth().height(120.dp),
                placeholder = { Text("Cuéntanos sobre su personalidad, temperamento y lo que necesita...", color = SecondaryGray.copy(alpha = 0.5f)) },
                shape = RoundedCornerShape(24.dp),
                maxLines = 5,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    focusedBorderColor = PrimaryCoral,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Submit Button
        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryCoral),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text("Publicar", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun PublishInputField(label: String, value: String, onValueChange: (String) -> Unit, placeholder: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder, color = SecondaryGray.copy(alpha = 0.5f)) },
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedBorderColor = PrimaryCoral,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PublishPreview() {
    AdoptaMETheme {
        PublishScreen(onBack = {})
    }
}
