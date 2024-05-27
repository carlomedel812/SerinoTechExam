package com.example.serinotechexam.koin

import com.example.serinotechexam.repositories.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ProductRepository(get()) }
}