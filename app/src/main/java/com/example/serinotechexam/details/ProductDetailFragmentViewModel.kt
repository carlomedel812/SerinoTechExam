package com.example.serinotechexam.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serinotechexam.model.Product
import com.example.serinotechexam.repositories.ProductRepository
import com.example.serinotechexam.utils.StateResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProductDetailFragmentViewModel(private val productRepository: ProductRepository): ViewModel() {

    suspend fun getProductDetails(id: Int): Flow<StateResult<Product>> {
       return productRepository.getProduct(id)
    }
}