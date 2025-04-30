package com.shoe.shoestore.controller

import com.shoe.shoestore.R
import com.shoe.shoestore.model.Shoe

object ShoeController {

    private val shoeList = mutableListOf<Shoe>()

    // Loading the initial shoes
    fun loadShoes(): List<Shoe> {
        if (shoeList.isEmpty()) {
            shoeList.addAll(
                listOf(
                    Shoe(
                        id = 1,
                        name = "Nike Air Max",
                        company = "Nike",
                        originalPrice = 100.0,
                        discountedPrice = 80.0,
                        rating = 4.5f,
                        imagesByColor = mapOf(
                            "red" to R.drawable.nike_air_red
                        )
                    ),
                    Shoe(
                        id = 2,
                        name = "Adidas Runner",
                        company = "Adidas",
                        originalPrice = 90.0,
                        discountedPrice = 70.0,
                        rating = 4.0f,
                        imagesByColor = mapOf(
                            "yellow" to R.drawable.adidas_runner_yellow
                        )
                    ),
                    Shoe(
                        id = 3,
                        name = "Nike Air Max",
                        company = "Nike",
                        originalPrice = 85.0,
                        discountedPrice = 65.0,
                        rating = 4.5f,
                        imagesByColor = mapOf(
                            "green" to R.drawable.nike_air_green
                        )
                    ),
                    Shoe(
                        id = 4,
                        name = "Nike Air Max",
                        company = "Nike",
                        originalPrice = 60.0,
                        discountedPrice = 45.0,
                        rating = 4.5f,
                        imagesByColor = mapOf(
                            "yellow" to R.drawable.nike_air_yellow
                        )
                    ),
                    Shoe(
                        id = 5,
                        name = "Adidas Runner",
                        company = "Adidas",
                        originalPrice = 80.0,
                        discountedPrice = 40.0,
                        rating = 4.0f,
                        imagesByColor = mapOf(
                            "red" to R.drawable.adidas_runner_red
                        )
                    ),
                    Shoe(
                        id = 6,
                        name = "Adidas Runner",
                        company = "Adidas",
                        originalPrice = 70.0,
                        discountedPrice = 50.0,
                        rating = 4.0f,
                        imagesByColor = mapOf(
                            "blue" to R.drawable.adidas_runner_blue
                        )
                    ),
                    Shoe(
                        id = 7,
                        name = "Adidas Runner",
                        company = "Adidas",
                        originalPrice = 50.0,
                        discountedPrice = 35.0,
                        rating = 4.0f,
                        imagesByColor = mapOf(
                            "green" to R.drawable.adidas_runner_green
                        )
                    ),
                    Shoe(
                        id = 8,
                        name = "Nike Air Max",
                        company = "Nike",
                        originalPrice = 80.0,
                        discountedPrice = 40.0,
                        rating = 4.5f,
                        imagesByColor = mapOf(
                            "blue" to R.drawable.nike_air_blue
                        )
                    ),
                    Shoe(
                        id = 9,
                        name = "Puma Runner",
                        company = "Puma",
                        originalPrice = 70.0,
                        discountedPrice = 65.0,
                        rating = 4.0f,
                        imagesByColor = mapOf(
                            "red" to R.drawable.puma_runner_red
                        )
                    ),
                    Shoe(
                        id = 10,
                        name = "Puma Runner",
                        company = "Puma",
                        originalPrice = 110.0,
                        discountedPrice = 65.0,
                        rating = 4.0f,
                        imagesByColor = mapOf(
                            "blue" to R.drawable.puma_runner_blue
                        )
                    ),
                    Shoe(
                        id = 11,
                        name = "Puma Runner",
                        company = "Puma",
                        originalPrice = 110.0,
                        discountedPrice = 70.0,
                        rating = 4.0f,
                        imagesByColor = mapOf(
                            "green" to R.drawable.puma_runner_green
                        )
                    ),
                    Shoe(
                        id = 12,
                        name = "Puma Runner",
                        company = "Puma",
                        originalPrice = 130.0,
                        discountedPrice = 110.0,
                        rating = 4.0f,
                        imagesByColor = mapOf(
                            "yellow" to R.drawable.puma_runner_yellow
                        )
                    ),
                    Shoe(
                        id = 13,
                        name = "Gucci Runner",
                        company = "Gucci",
                        originalPrice = 100.0,
                        discountedPrice = 70.0,
                        rating = 4.0f,
                        imagesByColor = mapOf(
                            "red" to R.drawable.gucci_runner_red
                        )
                    ),
                    Shoe(
                        id = 14,
                        name = "Gucci Runner",
                        company = "Gucci",
                        originalPrice = 90.0,
                        discountedPrice = 70.0,
                        rating = 4.0f,
                        imagesByColor = mapOf(
                            "blue" to R.drawable.gucci_runner_blue
                        )
                    ),
                    Shoe(
                        id = 15,
                        name = "Gucci Runner",
                        company = "Gucci",
                        originalPrice = 50.0,
                        discountedPrice = 40.0,
                        rating = 4.0f,
                        imagesByColor = mapOf(
                            "green" to R.drawable.gucci_runner_green
                        )
                    ),
                    Shoe(
                        id = 16,
                        name = "Gucci Runner",
                        company = "Gucci",
                        originalPrice = 80.0,
                        discountedPrice = 650.0,
                        rating = 4.0f,
                        imagesByColor = mapOf(
                            "yellow" to R.drawable.gucci_runner_yellow
                        )
                    ),
                )
            )
        }
        return shoeList
    }

    // Toggle the favorite status
    fun toggleFavorite(shoe: Shoe) {
        shoe.isFavorite = !shoe.isFavorite
    }

    // Update the selected color
    fun updateSelectedColor(shoe: Shoe, color: String) {
        shoe.selectedColor = color
    }

    // Get the favorites list
    fun getFavoriteShoes(): List<Shoe> {
        return shoeList.filter { it.isFavorite }
    }
}
