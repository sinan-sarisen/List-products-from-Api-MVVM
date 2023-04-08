package com.sinansarisen.demo.service.model

data class ProductResponse(
    val result: Result
)

data class Result(
    val nextUrl: String,
    val horizontalProducts: List<HorizontalProduct>,
    val products: List<Product>
)

data class HorizontalProduct(
    val code: Int,
    val imageUrl: String,
    val name: String,
    val dropRatio: Double,
    val price: Double,
    val countOfPrices: Int,
    val followCount: Int
)

data class Product(
    val code: Int,
    val imageUrl: String,
    val name: String,
    val dropRatio: Double,
    val price: Double,
    val countOfPrices: Int,
    val followCount: Int
)