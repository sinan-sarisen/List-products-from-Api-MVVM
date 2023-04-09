package com.sinansarisen.demo.service.model

data class ProductDetailResponse(
    val result: ResultDetails
)

data class ResultDetails(
    val badge: String,
    val countOfPrices: Int,
    val freeShipping: Boolean,
    val imageUrl: String,
    val lastUpdate: String,
    val mkName: String,
    val price: Int,
    val productName: String,
    val rating: Double,
    val storageOptions: List<String>
)