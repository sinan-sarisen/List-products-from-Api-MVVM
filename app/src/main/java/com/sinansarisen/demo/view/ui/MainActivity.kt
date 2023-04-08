package com.sinansarisen.demo.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sinansarisen.demo.*
import com.sinansarisen.demo.databinding.ActivityMainBinding
import com.sinansarisen.demo.service.model.HorizontalProduct
import com.sinansarisen.demo.service.repository.MainRepository
import com.sinansarisen.demo.service.repository.RetrofitService
import com.sinansarisen.demo.view.adapter.ProductAdapter
import com.sinansarisen.demo.viewmodel.MainViewModel
import com.sinansarisen.demo.viewmodel.MyViewModelFactory
import me.relex.circleindicator.CircleIndicator

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = ProductAdapter()
    private var imagesModel:HorizontalProduct? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java)

        binding.recyclerview.adapter = adapter

        viewModel.productList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setProductList(it)
        })



        viewModel.errorMessage.observe(this, Observer {

        })
        viewModel.getAllProducts()
    }
}