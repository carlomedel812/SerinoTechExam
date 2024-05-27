package com.example.serinotechexam.repositories

import com.example.serinotechexam.model.Product
import com.example.serinotechexam.network.response.ProductResponse
import com.example.serinotechexam.network.ProductsApiInterface
import com.example.serinotechexam.utils.StateResult
import com.example.serinotechexam.utils.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository(private val api: ProductsApiInterface) {

    suspend fun getProduct(id: Int): Flow<StateResult<Product>> {
        return flow {
            emit(api.getProductById(id))
        }.asResult()
    }

    suspend fun getProducts(skip: Int, limit: Int): ProductResponse {
        return api.getProducts(skip, limit)
    }
}