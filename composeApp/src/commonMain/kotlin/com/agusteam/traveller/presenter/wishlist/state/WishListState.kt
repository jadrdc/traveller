package com.agusteam.traveller.presenter.wishlist.state

import com.agusteam.traveller.domain.models.TripModel

data class WishListState(
    val favoriteItems: List<TripModel> = listOf(),
)
