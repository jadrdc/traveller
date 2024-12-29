package com.agusteam.traveller.presenter.profile.state

import com.agusteam.traveller.core.base.ViewModelState
import com.agusteam.traveller.domain.models.TripModel
import com.agusteam.traveller.domain.models.TripProviderModel
import com.agusteam.traveller.presenter.createCategories
import com.agusteam.traveller.presenter.createShoppingItems

data class TripProviderState(
    val isLoading: Boolean = true,
    val tripProviderModel: TripProviderModel? = null,
    val upcomingTrips: List<TripModel> = createShoppingItems(createCategories())
) : ViewModelState
