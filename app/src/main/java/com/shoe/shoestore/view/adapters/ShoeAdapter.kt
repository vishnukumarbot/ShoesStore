package com.shoe.shoestore.view.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shoe.shoestore.R
import com.shoe.shoestore.databinding.ItemShoeBinding
import com.shoe.shoestore.model.Shoe

class ShoeAdapter(
    private val shoeList: List<Shoe>,
    private val onShoeClick: (Shoe) -> Unit,
    private val onFavoriteClick: (Shoe) -> Unit
) : RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder>() {

    inner class ShoeViewHolder(val binding: ItemShoeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val binding = ItemShoeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        val shoe = shoeList[position]
        holder.binding.apply {
            // Load the shoe image based on selected color
            Glide.with(root.context)
                .load(shoe.imagesByColor.values.first())
                .into(imgShoe)

            txtShoeName.text = shoe.name
            txtDiscountedPrice.text = "$. ${shoe.discountedPrice}"
            txtOriginalPrice.text = "$. ${shoe.originalPrice}"
            txtOriginalPrice.paintFlags = txtOriginalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            // Set the favorite icon
            imgFavorite.setImageResource(
                if (shoe.isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_border
            )

            // Click the Listeners
            root.setOnClickListener { onShoeClick(shoe) }
            imgFavorite.setOnClickListener { onFavoriteClick(shoe) }
        }
    }

    override fun getItemCount(): Int = shoeList.size
}
