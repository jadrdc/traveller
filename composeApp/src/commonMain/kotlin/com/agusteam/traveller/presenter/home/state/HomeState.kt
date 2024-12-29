package com.agusteam.traveller.presenter.home.state

import com.agusteam.traveller.core.base.ViewModelState
import com.agusteam.traveller.domain.models.ErrorModel

data class HomeState(
    val currentNavigationOption: HomeOption = HomeOption.EXPLORE,
    val isLoading: Boolean = false,
    val errorModel: ErrorModel? = null
) : ViewModelState

enum class HomeOption {
    EXPLORE, FAVORITE, TRIP, PROFILE, SHOPPING_ITEM_DETAIL, WISHLIST
}