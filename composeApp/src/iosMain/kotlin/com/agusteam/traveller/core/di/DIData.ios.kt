package com.agusteam.traveller.core.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.agusteam.traveller.data.database.createDataStore
import com.agusteam.traveller.data.network.createHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val networkModule: Module
    get() = module {
        single<HttpClient> { createHttpClient(Darwin.create()) }
    }
actual val dataStorageDIModule: Module
    get() = module {
        single<DataStore<Preferences>> {
            createDataStore()
        }
    }