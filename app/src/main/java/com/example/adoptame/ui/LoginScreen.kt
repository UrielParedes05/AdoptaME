package com.example.adoptame.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoptame.ui.theme.AdoptaMETheme
import com.example.adoptame.ui.theme.PrimaryCoral
import com.example.adoptame.ui.theme.SecondaryGray

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Logo Placeholder (Paw)
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color(0xFFFFE8E3), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("🐾", fontSize = 40.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // App Name
        Row {
            Text(
                text = "Patitas ",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryCoral
            )
            Text(
                text = "App",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Text(
            text = "¡Encuentra a tu compañero perfecto!",
            color = SecondaryGray,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Inputs
        LoginInput(
            label = "Correo electrónico",
            value = email,
            onValueChange = { email = it },
            placeholder = "hola@tupatita.com",
            leadingIcon = Icons.Default.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        LoginInput(
            label = "Contraseña",
            value = password,
            onValueChange = { password = it },
            placeholder = "••••••••••••",
            leadingIcon = Icons.Default.Lock,
            trailingIcon = Icons.Default.VisibilityOff,
            isPassword = true
        )

        Text(
            text = "¿Olvidaste tu contraseña?",
            color = PrimaryCoral.copy(alpha = 0.7f),
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.End)
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Action Buttons
        Button(
            onClick = { /* Login */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryCoral),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text("Iniciar Sesión", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { /* Create account */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text("Crear Cuenta", color = Color.Black, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Divider
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE0E0E0))
            Text(
                text = " o continúa con ",
                modifier = Modifier.padding(horizontal = 8.dp),
                color = SecondaryGray,
                fontSize = 12.sp
            )
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE0E0E0))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Social Login
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SocialButton(
                text = "Google",
                icon = "🌐",
                modifier = Modifier.weight(1f)
            )
            SocialButton(
                text = "Apple",
                icon = "🍎",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Adopta, no compres ❤️",
            color = SecondaryGray,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
fun LoginInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector? = null,
    isPassword: Boolean = false
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder, color = SecondaryGray.copy(alpha = 0.5f)) },
            leadingIcon = { Icon(leadingIcon, contentDescription = null, tint = SecondaryGray) },
            trailingIcon = trailingIcon?.let {
                { Icon(it, contentDescription = null, tint = SecondaryGray) }
            },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Email),
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

@Composable
fun SocialButton(text: String, icon: String, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = { },
        modifier = modifier.height(56.dp),
        shape = RoundedCornerShape(28.dp),
        border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(icon, fontSize = 20.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text, color = Color.Black, fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    AdoptaMETheme {
        LoginScreen()
    }
}
