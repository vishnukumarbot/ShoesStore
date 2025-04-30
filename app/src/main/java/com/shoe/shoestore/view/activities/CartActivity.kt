package com.shoe.shoestore.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.shoe.shoestore.R
import com.shoe.shoestore.controller.CartController
import com.shoe.shoestore.databinding.ActivityCartBinding
import com.shoe.shoestore.view.adapters.CartAdapter

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupCheckoutButton()

        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_cart -> {
                    val intent = Intent(this, CartActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    private fun setupRecyclerView() {
        val cartItems = CartController.getCartItems()

        cartAdapter = CartAdapter(
            cartItems,
            onRemoveClick = { cartItem ->
                CartController.removeFromCart(cartItem)
                cartAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Item removed from Cart", Toast.LENGTH_SHORT).show()
            }
        )

        binding.rvCart.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = cartAdapter
        }
        updateUI()
    }

    private fun setupCheckoutButton() {
        binding.btnCheckout.setOnClickListener {
            Toast.makeText(this, "Checkout successful!", Toast.LENGTH_SHORT).show()
            CartController.clearCart()
            cartAdapter.notifyDataSetChanged()
        }
    }
    private fun updateUI() {
        val cartItems = CartController.getCartItems()
        if (cartItems.isEmpty()) {
            binding.rvCart.visibility = View.GONE
            binding.txtEmptyCart.visibility = View.VISIBLE
            binding.btnCheckout.visibility = View.GONE
        } else {
            binding.rvCart.visibility = View.VISIBLE
            binding.txtEmptyCart.visibility = View.GONE
            binding.btnCheckout.visibility = View.VISIBLE
        }
    }

}
