package com.agusteam.traveller.di

import com.agusteam.traveller.data.network.createHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

 val networkModule: Module
    get() = module {
        single<HttpClient>{createHttpClient(OkHttp.create())}
    }