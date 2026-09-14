package com.example.adoptame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.*
import com.example.adoptame.ui.MainScreen
import com.example.adoptame.ui.LoginScreen
import com.example.adoptame.ui.theme.AdoptaMETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdoptaMETheme {
                var currentScreen by remember { mutableStateOf("login") }

                Crossfade(targetState = currentScreen, label = "ScreenTransition") { screen ->
                    when (screen) {
                        "login" -> LoginScreen(onLoginSuccess = { currentScreen = "home" })
                        "home" -> MainScreen(onLogout = { currentScreen = "login" })
                    }
                }
            }
        }
    }
}
