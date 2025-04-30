package com.shoe.shoestore.model

data class Shoe(
    val id: Int,
    val name: String,
    val company: String,
    val originalPrice: Double,
    val discountedPrice: Double,
    val rating: Float, // Shoe rating (e.g., 4.5)
    val imagesByColor: Map<String, Int>, // Color name to drawable resource ID
    var selectedColor: String = "red", // Default selected color
    var isFavorite: Boolean = false    // Favorite toggle
)
