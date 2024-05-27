package com.example.serinotechexam.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Product(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("price") val price: BigDecimal,
    @SerializedName("images") val images: List<String>
)
