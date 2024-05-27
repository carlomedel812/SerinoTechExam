package com.example.serinotechexam.lists

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.serinotechexam.factory.ProductsDataSourceFactory
import com.example.serinotechexam.model.Product
import com.example.serinotechexam.network.ProductsApiInterface
import com.example.serinotechexam.utils.StateResult
import com.example.serinotechexam.repositories.ProductRepository
import kotlinx.coroutines.launch

class ProductsListFragmentViewModel(
    private val productSourceFactory: ProductsDataSourceFactory,
    private val api: ProductsApiInterface
) : ViewModel() {

    val products = productSourceFactory.getProducts().cachedIn(viewModelScope)
}