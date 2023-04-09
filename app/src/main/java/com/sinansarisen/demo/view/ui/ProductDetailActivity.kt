package com.sinansarisen.demo.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.sinansarisen.demo.R
import com.sinansarisen.demo.databinding.ActivityProductDetailBinding
import com.sinansarisen.demo.service.model.ResultDetails
import com.sinansarisen.demo.utils.toPrice

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ratingBar.isEnabled = false
        binding.ratingBar.max = 5
        binding.ratingBar.stepSize = 0.01f
        binding.ratingBar.invalidate()
        val resultDetails = intent.getParcelableExtra<ResultDetails>("resultDetails")

        resultDetails?.let {
            binding.productName.text = it.productName

            binding.price.text = it.price.toPrice('₺')

            Glide.with(this)
                .load(it.imageUrl)
                .into(binding.imageview)

            binding.ratingBar.rating = it.rating.toFloat()

            binding.mkName.text= it.mkName

            binding.sellerCount.text = "${it.countOfPrices} satıcı içinde kargo dahil en uygun fiyat seçeneği"

            binding.latestUpdate.text = "Son güncelleme: ${it.lastUpdate} "

            if(it.freeShipping){
                binding.freeShipping.visibility = View.VISIBLE
            }else
                binding.freeShipping.visibility = View.GONE


            for (name in it.storageOptions) {
                val button = Button(this)
                button.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                button.text = name
                binding.buttonContainer.addView(button)
            }
        }
    }
}