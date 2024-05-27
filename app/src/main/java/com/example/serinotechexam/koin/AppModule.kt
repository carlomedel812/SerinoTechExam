package com.example.serinotechexam.koin

import android.app.Application
import org.koin.dsl.module

fun appModule(application: Application) = module {
    single { application }
}