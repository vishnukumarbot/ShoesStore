package com.shoe.shoestore.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shoe.shoestore.databinding.ItemColorBinding
import com.shoe.shoestore.R

class ColorAdapter(
    private val colors: List<String>,
    private var selectedColor: String,
    private val onColorSelected: (String) -> Unit
) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val binding = ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val color = colors[position]

        // Set the color background
        holder.binding.colorBox.setBackgroundColor(
            when (color) {
                "red" -> ContextCompat.getColor(holder.binding.root.context, android.R.color.holo_red_dark)
                "blue" -> ContextCompat.getColor(holder.binding.root.context, android.R.color.holo_blue_dark)
                "green" -> ContextCompat.getColor(holder.binding.root.context, android.R.color.holo_green_dark)
                "yellow" -> ContextCompat.getColor(holder.binding.root.context, android.R.color.holo_orange_light)
                else -> ContextCompat.getColor(holder.binding.root.context, android.R.color.darker_gray)
            }
        )

        // Highlight the selected color
        if (color == selectedColor) {
            holder.binding.colorBox.background = ContextCompat.getDrawable(holder.binding.root.context, R.drawable.selected_color_border)
        } else {
            holder.binding.colorBox.background = null
        }

        holder.binding.root.setOnClickListener {
            onColorSelected(color)
        }
    }

    override fun getItemCount(): Int = colors.size

    fun updateSelectedColor(color: String) {
        selectedColor = color
        notifyDataSetChanged()
    }

    inner class ColorViewHolder(val binding: ItemColorBinding) : RecyclerView.ViewHolder(binding.root)
}
