package com.sinansarisen.demo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sinansarisen.demo.databinding.AdapterProductBinding
import com.sinansarisen.demo.service.model.Product

class ProductAdapter: RecyclerView.Adapter<MainViewHolder>() {

    var products = mutableListOf<Product>()

    fun setProductList(products: List<Product>) {
        this.products = products.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterProductBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val product = products[position]
        holder.binding.name.text = product.name
        Glide.with(holder.itemView.context).load(product.imageUrl).into(holder.binding.imageview)

    }

    override fun getItemCount(): Int {
        return products.size
    }
}

class MainViewHolder(val binding: AdapterProductBinding) : RecyclerView.ViewHolder(binding.root) {

}
