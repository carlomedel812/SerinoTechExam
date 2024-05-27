package com.example.serinotechexam.network

import com.example.serinotechexam.model.Product
import com.example.serinotechexam.network.response.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApiInterface {
    @GET("/products")
    suspend fun getProducts(@Query("skip") skip: Int, @Query("limit") limit: Int): ProductResponse

    @GET("/products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
}