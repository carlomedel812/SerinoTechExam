package com.example.serinotechexam.koin

import com.example.serinotechexam.factory.ProductsDataSourceFactory
import org.koin.dsl.module

val factoryModule = module {
    single { ProductsDataSourceFactory(get()) }
}