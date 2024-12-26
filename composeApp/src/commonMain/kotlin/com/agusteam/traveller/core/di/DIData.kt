package com.agusteam.traveller.core.di

import com.agusteam.traveller.data.network.services.CategoryService
import com.agusteam.traveller.data.network.services.SignUpService
import com.agusteam.traveller.data.network.services.TripProviderService
import org.koin.core.module.Module
import org.koin.dsl.module

expect val networkModule: Module
expect val dataStorageDIModule:Module
val dataDiModule = module {
    single<SignUpService> { SignUpService(get()) }
    single<CategoryService> { CategoryService(get()) }
    single<TripProviderService> { TripProviderService(get()) }
}