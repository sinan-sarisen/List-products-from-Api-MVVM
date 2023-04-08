package com.sinansarisen.demo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sinansarisen.demo.service.model.HorizontalProduct
import com.sinansarisen.demo.service.model.Product
import com.sinansarisen.demo.service.model.ProductResponse
import com.sinansarisen.demo.service.repository.MainRepository
import com.sinansarisen.demo.service.repository.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val productList = MutableLiveData<List<Product>>()
    val horizontalProductList = MutableLiveData<List<HorizontalProduct>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllProducts() {

        repository.getAllProducts().enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    val productResponse = response.body()
                    productResponse?.let {
                        val products = it.result.products
                        productList.postValue(products)
                        val horizontalProducts = it.result.horizontalProducts
                        horizontalProductList.postValue(horizontalProducts)
                    }
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.e("MainViewModel", "Error fetching products", t)
            }
        })
    }
}