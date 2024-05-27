package com.example.serinotechexam

import com.example.serinotechexam.koin.appModule
import com.example.serinotechexam.koin.factoryModule
import com.example.serinotechexam.koin.networkModule
import com.example.serinotechexam.koin.repositoryModule
import com.example.serinotechexam.koin.viewModelModule
import org.koin.core.context.startKoin

class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            modules(listOf(
                appModule(this@Application),
                networkModule(this@Application),
                repositoryModule,
                factoryModule,
                viewModelModule
            ))
        }
    }
}