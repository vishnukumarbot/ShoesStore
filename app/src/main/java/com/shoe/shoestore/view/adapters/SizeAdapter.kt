package com.shoe.shoestore.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoe.shoestore.R
import com.shoe.shoestore.databinding.ItemSizeBinding

class SizeAdapter(
    private val sizes: List<Int>,
    private val onSizeSelected: (Int) -> Unit
) : RecyclerView.Adapter<SizeAdapter.SizeViewHolder>() {

    private var selectedPosition = 2 // Just a default selected value, will be updated based on the user's selection.

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val binding = ItemSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SizeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        val size = sizes[position]
        holder.binding.txtSize.text = size.toString()

        holder.binding.txtSize.isSelected = position == selectedPosition

        // Highlight the selected size
        if (position == selectedPosition) {
            holder.binding.txtSize.setBackgroundResource(android.R.color.holo_blue_light)
        } else {
            holder.binding.txtSize.setBackgroundResource(R.drawable.size_box_background)
        }

        holder.binding.txtSize.setOnClickListener {
            selectedPosition = holder.adapterPosition
            onSizeSelected(size)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = sizes.size

    inner class SizeViewHolder(val binding: ItemSizeBinding) : RecyclerView.ViewHolder(binding.root)
}
