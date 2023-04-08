package com.sinansarisen.demo.view.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sinansarisen.demo.databinding.ItemHolderBinding

class ViewPagerAdapter(
    private val context: Context,
    private val labelList: MutableList<String>) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val binding = ItemHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val label = labelList[position]
        holder.bind(label)
    }

    override fun getItemCount(): Int {
        return labelList.size
    }

    class ViewPagerHolder(private var itemHolderBinding: ItemHolderBinding) :
        RecyclerView.ViewHolder(itemHolderBinding.root) {
        fun bind(label: String) {
            itemHolderBinding.nameTextView.text = label
        }
    }
}
