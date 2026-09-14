package com.example.adoptame.models

data class Pet(
    val id: Int,
    val name: String,
    val breed: String,
    val age: String,
    val description: String,
    val publisherName: String,
    val publisherAvatar: Int? = null,
    val imageRes: Int? = null,
    val isFavorite: Boolean = false,
    val category: String // "Perros", "Gatos", "Otros"
)
