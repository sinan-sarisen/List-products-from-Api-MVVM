package com.sinansarisen.demo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sinansarisen.demo.databinding.HorizontalItemHolderBinding
import com.sinansarisen.demo.service.model.HorizontalProduct
import com.sinansarisen.demo.service.model.Product
import com.sinansarisen.demo.view.ui.toPrice

class ViewPagerAdapter(
    private val context: Context,
    private var productList: MutableList<HorizontalProduct>) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {


    fun setProductList(products: List<HorizontalProduct>) {
        this.productList = products.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val binding = HorizontalItemHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val product = productList[position]
        product.price.toPrice('₺')?.let { holder.bind(product.name, it, product.imageUrl) }

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ViewPagerHolder(private var itemHolderBinding: HorizontalItemHolderBinding) :
        RecyclerView.ViewHolder(itemHolderBinding.root) {
        fun bind(name: String, price: String, imageUrl: String) {
            itemHolderBinding.nameTextView.text = name
            itemHolderBinding.priceTextView.text = price
            Glide.with(itemView.context).load(imageUrl).into(itemHolderBinding.imageView)
        }
    }
}
