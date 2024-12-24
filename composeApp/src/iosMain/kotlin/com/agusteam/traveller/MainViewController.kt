package com.agusteam.traveller

import androidx.compose.ui.window.ComposeUIViewController
import com.agusteam.traveller.core.di.dataDiModule
import com.agusteam.traveller.core.di.diDomainModule
import com.agusteam.traveller.core.di.networkModule
import com.agusteam.traveller.core.di.viewModelModule
import com.agusteam.traveller.presenter.signup.navigation.MainNavigationFlow
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController(configure = {
    startKoin { modules(diDomainModule, viewModelModule, dataDiModule, networkModule) }
}) {
    MainNavigationFlow()
}