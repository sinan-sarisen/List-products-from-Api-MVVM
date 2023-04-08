package com.sinansarisen.demo.service.repository

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllProducts() = retrofitService.getAllProducts()
}