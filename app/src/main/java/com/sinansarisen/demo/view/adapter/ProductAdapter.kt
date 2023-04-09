package com.sinansarisen.demo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sinansarisen.demo.databinding.AdapterProductBinding
import com.sinansarisen.demo.service.model.Product
import com.sinansarisen.demo.service.repository.ProductItemClickListener
import com.sinansarisen.demo.utils.toPrice
import com.sinansarisen.demo.viewmodel.MainViewModel

class ProductAdapter(private var listener: ProductItemClickListener): RecyclerView.Adapter<MainViewHolder>() {

    var products = mutableListOf<Product>()
    lateinit var viewModel: MainViewModel


    fun setItemClickListener(listener: ProductItemClickListener) {
        this.listener = listener
    }

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
        holder.binding.productName.text = product.name
        holder.binding.price.text = product.price.toPrice('₺')
        Glide.with(holder.itemView.context).load(product.imageUrl).into(holder.binding.imageview)

        holder.itemView.setOnClickListener(View.OnClickListener {

            println(product.code)
            listener.onProductItemClick(product.code.toString())
        })
    }

    override fun getItemCount(): Int {
        return products.size
    }
}

class MainViewHolder(val binding: AdapterProductBinding) : RecyclerView.ViewHolder(binding.root) {

}
