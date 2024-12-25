package com.agusteam.traveller.app

import android.app.Application
import com.agusteam.traveller.core.di.dataDiModule
import com.agusteam.traveller.core.di.dataStorageDIModule
import com.agusteam.traveller.core.di.diDomainModule
import com.agusteam.traveller.core.di.networkModule
import com.agusteam.traveller.core.di.viewModelModule
import com.stripe.android.PaymentConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TravellerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TravellerApp)
            modules(
                viewModelModule,
                diDomainModule,
                networkModule,
                dataDiModule,
                dataStorageDIModule
            )
        }
        PaymentConfiguration.init(
            applicationContext,
            publishableKey = "pk_test_51QXqqtJyClKQPN2TFutzGFEqZAmG9zO6ftbN0A380KxuR9B1fAvXB09vF5gbyWGD7o5oYwGIIQwKIupTwaqLScTC00bLaLNM8C"
        )
    }
}