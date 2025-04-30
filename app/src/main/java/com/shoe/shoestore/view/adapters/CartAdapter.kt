package com.shoe.shoestore.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shoe.shoestore.databinding.ItemCartBinding
import com.shoe.shoestore.model.Cart

class CartAdapter(
    private val cartList: List<Cart>,
    private val onRemoveClick: (Cart) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartList[position]
        holder.binding.apply {
            txtCartShoeName.text = cartItem.shoe.name
            txtCartPrice.text = "$. ${cartItem.shoe.discountedPrice}"
            txtCartColor.text = "Color: ${cartItem.selectedColor}"
            txtCartSize.text = "Size: ${cartItem.selectedSize}"

            Glide.with(root.context)
                .load(cartItem.shoe.imagesByColor[cartItem.selectedColor])
                .into(imgCartShoe)

            btnRemove.setOnClickListener {
                onRemoveClick(cartItem)
            }
        }
    }

    override fun getItemCount(): Int = cartList.size
}
