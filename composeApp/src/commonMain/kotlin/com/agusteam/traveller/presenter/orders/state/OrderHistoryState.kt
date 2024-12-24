package com.agusteam.traveller.presenter.orders.state

import com.agusteam.traveller.domain.models.TripModel

data class OrderHistoryState(
    val upcomingItems: List<TripModel> = listOf(),
    val oldItems: List<TripModel> = listOf()
)
