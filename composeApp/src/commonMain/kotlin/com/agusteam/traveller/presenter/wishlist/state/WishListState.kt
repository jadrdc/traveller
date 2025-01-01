package com.agusteam.traveller.presenter.wishlist.state

import com.agusteam.traveller.core.base.ViewModelState
import com.agusteam.traveller.domain.models.ErrorModel
import com.agusteam.traveller.domain.models.TripModel

data class WishListState(
    val errorModel: ErrorModel? = null,
    val favoriteItems: List<TripModel> = listOf(),
    val isLoading: Boolean = false,
    val errorState: Boolean = false
) : ViewModelState
