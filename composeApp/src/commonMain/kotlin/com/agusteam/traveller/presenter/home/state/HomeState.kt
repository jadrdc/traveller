package com.agusteam.traveller.presenter.home.state

import androidx.compose.runtime.mutableStateOf
import com.agusteam.traveller.core.base.ViewModelState

class HomeState : ViewModelState {
    var currentNavigationOption = mutableStateOf(HomeOption.EXPLORE)

    fun changeHomeOption(option: HomeOption) {
        currentNavigationOption.value = option
    }
}

enum class HomeOption {
    EXPLORE, FAVORITE, TRIP, PROFILE, SHOPPING_ITEM_DETAIL, WISHLIST
}