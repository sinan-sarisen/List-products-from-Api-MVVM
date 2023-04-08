package com.sinansarisen.demo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sinansarisen.demo.R
import com.sinansarisen.demo.service.model.HorizontalProduct

class HorizontalProductAdapter: ListAdapter<HorizontalProduct, HorizontalProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_card, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val horizontalProduct = getItem(position)
        holder.bind(horizontalProduct)
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)

        fun bind(product: HorizontalProduct) {
            Glide.with(imageView)
                .load(product.imageUrl)
                .centerCrop()
                .into(imageView)
            nameTextView.text = product.name
            priceTextView.text = product.price.toString()
        }
    }
}
