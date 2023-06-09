package com.sinansarisen.demo.service.repository

import com.sinansarisen.demo.service.model.ProductDetailResponse
import com.sinansarisen.demo.service.model.ProductResponse
import com.sinansarisen.demo.utils.Constants.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("v1/59906f35-d5d5-40f7-8d44-53fd26eb3a05")
    fun getAllProducts() : Call<ProductResponse>

    @GET("v1/1a1fb542-22d1-4919-914a-750114879775")
    fun getProductDetail(@Query("code") code: String) : Call<ProductDetailResponse>

    companion object {

        private var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}
