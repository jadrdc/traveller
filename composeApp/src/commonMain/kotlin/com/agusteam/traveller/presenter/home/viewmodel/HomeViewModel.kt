package com.agusteam.traveller.presenter.home.viewmodel

import com.agusteam.traveller.core.base.GenericViewModel
import com.agusteam.traveller.presenter.home.state.HomeState

class HomeViewModel : GenericViewModel<HomeState, HomeViewModel.HomeEvent>(HomeState()) {


    sealed interface HomeEvent {
    }
}