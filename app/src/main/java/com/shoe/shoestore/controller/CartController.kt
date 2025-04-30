package com.shoe.shoestore.controller

import com.shoe.shoestore.model.Cart
import com.shoe.shoestore.model.Shoe

object CartController {

    private val cartItems = mutableListOf<Cart>()

    // Add a shoe to cart
    fun addToCart(shoe: Shoe, selectedColor: String, selectedSize: Int) {
        cartItems.add(Cart(shoe, selectedColor, selectedSize))
    }

    // Remove a shoe from cart
    fun removeFromCart(cartItem: Cart) {
        cartItems.remove(cartItem)
    }

    // Get all the cart items
    fun getCartItems(): List<Cart> {
        return cartItems
    }

    // Get total price of cart
    fun getCartTotal(): Double {
        return cartItems.sumOf { it.shoe.discountedPrice }
    }

    // Clear cart
    fun clearCart() {
        cartItems.clear()
    }
}
