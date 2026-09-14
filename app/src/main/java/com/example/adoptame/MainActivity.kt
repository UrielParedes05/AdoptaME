package com.example.adoptame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.adoptame.ui.LoginScreen
import com.example.adoptame.ui.theme.AdoptaMETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdoptaMETheme {
                LoginScreen()
            }
        }
    }
}
