package com.example.serinotechexam.factory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.serinotechexam.model.Product
import com.example.serinotechexam.network.response.ProductResponse
import com.example.serinotechexam.repositories.ProductRepository
import com.example.serinotechexam.utils.Config
import com.example.serinotechexam.utils.PagingDataSource
import kotlinx.coroutines.flow.map

class ProductsDataSourceFactory(private val productRepository: ProductRepository) {
    companion object {
        private const val PAGE_SIZE = 10
    }

    fun getProducts(): LiveData<PagingData<Product>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PagingDataSource<Product>(
                    config = Config<Product>(
                        fetch = { skip, limit -> productRepository.getProducts(skip, limit) },
                        list = { (it as ProductResponse).products },
                        skip = PAGE_SIZE,
                        hasNextPage = { (it as ProductResponse).total > (it as ProductResponse).skip }
                    )
                )
            }
        )

        return pager.liveData
    }
}