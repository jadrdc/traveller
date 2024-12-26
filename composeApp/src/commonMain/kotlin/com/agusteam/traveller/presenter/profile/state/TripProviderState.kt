package com.agusteam.traveller.presenter.profile.state

import com.agusteam.traveller.core.base.ViewModelState
import com.agusteam.traveller.domain.models.TripProviderModel

data class TripProviderState(
    val isLoading: Boolean = true,
    val tripProviderModel: TripProviderModel? = null
) : ViewModelState
