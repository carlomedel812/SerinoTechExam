package com.example.serinotechexam.koin

import com.example.serinotechexam.details.ProductDetailFragmentViewModel
import com.example.serinotechexam.lists.ProductsListFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ProductsListFragmentViewModel(get(), get()) }
    viewModel { ProductDetailFragmentViewModel(get()) }
}