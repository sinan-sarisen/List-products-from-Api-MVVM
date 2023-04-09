package com.sinansarisen.demo.view.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sinansarisen.demo.*
import com.sinansarisen.demo.databinding.ActivityMainBinding
import com.sinansarisen.demo.service.model.ResultDetails
import com.sinansarisen.demo.service.repository.MainRepository
import com.sinansarisen.demo.service.repository.ProductItemClickListener
import com.sinansarisen.demo.service.repository.RetrofitService
import com.sinansarisen.demo.view.adapter.ProductAdapter
import com.sinansarisen.demo.view.adapter.ViewPagerAdapter
import com.sinansarisen.demo.viewmodel.MainViewModel
import com.sinansarisen.demo.viewmodel.MyViewModelFactory
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity(), ProductItemClickListener {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = ProductAdapter(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java)
        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.adapter = adapter

        viewModel.productList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setProductList(it)
        })

        viewModel.horizontalProductList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            binding.viewPager2.adapter = ViewPagerAdapter(this, it.toMutableList())
            binding.indicator.setViewPager(this.binding.viewPager2)

        })

        viewModel.productDetailData.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            val intent = Intent(this, ProductDetailActivity::class.java).apply {
                putExtra("resultDetails", it)
            }
            startActivity(intent)

        })

        viewModel.errorMessage.observe(this, Observer {

        })
        viewModel.getAllProducts()
    }

    override fun onProductItemClick(code: String) {
        viewModel.getProductDetail(code)
    }

}
