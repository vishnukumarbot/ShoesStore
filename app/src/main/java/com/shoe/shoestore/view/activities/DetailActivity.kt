package com.shoe.shoestore.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.shoe.shoestore.R
import com.shoe.shoestore.controller.CartController
import com.shoe.shoestore.controller.ShoeController
import com.shoe.shoestore.databinding.ActivityDetailBinding
import com.shoe.shoestore.model.Shoe
import com.shoe.shoestore.view.adapters.ColorAdapter
import com.shoe.shoestore.view.adapters.SizeAdapter

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var selectedShoe: Shoe
    private lateinit var colorAdapter: ColorAdapter
    private lateinit var sizeAdapter: SizeAdapter

    private var selectedSize: Int = 2 // Default shoe size

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shoeId = intent.getIntExtra("shoeId", -1)
        selectedShoe = ShoeController.loadShoes().first { it.id == shoeId }

        setupUI()
        setupSizeRecyclerView()
        setupColorRecyclerView()
        setupAddToCartButton()
        setupFavoriteButton()
        setupBottomNavigation()
    }

    private fun setupUI() {
        updateShoeDisplay()
        updateFavoriteIcon()

        binding.txtDetailShoeName.text = selectedShoe.name
        binding.txtDetailPrice.text = "$. ${selectedShoe.discountedPrice}"
        binding.ratingBar.rating = selectedShoe.rating
    }

    private fun setupSizeRecyclerView() {
        val sizes = listOf(38, 39, 40, 41, 42, 43, 44, 45)

        sizeAdapter = SizeAdapter(
            sizes,
            onSizeSelected = { size ->
                selectedSize = size
            }
        )

        binding.rvSizes.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = sizeAdapter
        }
    }

    private fun setupColorRecyclerView() {
        val colors = selectedShoe.imagesByColor.keys.toList()

        colorAdapter = ColorAdapter(
            colors,
            selectedShoe.selectedColor,
            onColorSelected = { color ->
                ShoeController.updateSelectedColor(selectedShoe, color)
                updateShoeDisplay()
                colorAdapter.updateSelectedColor(color)
            }
        )

        binding.rvColors.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = colorAdapter
        }
    }

    private fun updateShoeDisplay() {
        val imageResId = selectedShoe.imagesByColor[selectedShoe.selectedColor]
        if (imageResId != null) {
            Glide.with(this)
                .load(imageResId)
                .into(binding.imgDetailShoe)
        }
    }

    private fun setupAddToCartButton() {
        binding.btnAddToCart.setOnClickListener {
            CartController.addToCart(selectedShoe, selectedShoe.selectedColor, selectedSize)
            Toast.makeText(this, "Added to Cart", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupFavoriteButton() {
        binding.btnFavoriteDetail.setOnClickListener {
            ShoeController.toggleFavorite(selectedShoe)
            updateFavoriteIcon()
        }
    }

    private fun updateFavoriteIcon() {
        if (selectedShoe.isFavorite) {
            binding.btnFavoriteDetail.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            binding.btnFavoriteDetail.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.nav_cart -> {
                    startActivity(Intent(this, CartActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
