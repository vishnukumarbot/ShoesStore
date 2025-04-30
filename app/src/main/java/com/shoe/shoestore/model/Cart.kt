package com.shoe.shoestore.model

data class Cart(
    val shoe: Shoe,
    val selectedColor: String,
    val selectedSize: Int
)

