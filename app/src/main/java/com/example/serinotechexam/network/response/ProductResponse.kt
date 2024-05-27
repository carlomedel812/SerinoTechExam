package com.example.serinotechexam.network.response

import com.example.serinotechexam.model.Product
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("products") val products: List<Product>,
    @SerializedName("total") val total: Int,
    @SerializedName("skip") val skip: Int,
    @SerializedName("limit") val limit: Int
)
