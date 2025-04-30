package com.shoe.shoestore.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.shoe.shoestore.R
import com.shoe.shoestore.controller.ShoeController
import com.shoe.shoestore.databinding.ActivityMainBinding
import com.shoe.shoestore.model.Shoe
import com.shoe.shoestore.view.adapters.ShoeAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var shoeAdapter: ShoeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

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
        val shoeList = ShoeController.loadShoes()

        shoeAdapter = ShoeAdapter(
            shoeList,
            onShoeClick = { selectedShoe ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("shoeId", selectedShoe.id)
                startActivity(intent)
            },
            onFavoriteClick = { shoe ->
                ShoeController.toggleFavorite(shoe)
                shoeAdapter.notifyDataSetChanged()
            }
        )

        binding.rvShoes.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = shoeAdapter
        }
    }
}
