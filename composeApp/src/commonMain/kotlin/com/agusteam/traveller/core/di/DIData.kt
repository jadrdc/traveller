package com.agusteam.traveller.core.di

import com.agusteam.traveller.data.network.services.SignUpService
import org.koin.core.module.Module
import org.koin.dsl.module

expect val networkModule: Module
val dataDiModule = module {
    single<SignUpService> { SignUpService(get()) }
}