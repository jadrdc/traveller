package com.agusteam.traveller.presenter.profile.viewmodels

import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.core.base.GenericViewModel
import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.usecase.GetTripProviderDetailsUseCase
import com.agusteam.traveller.domain.usecase.GetUpcomingTripByProviderUseCase
import com.agusteam.traveller.presenter.profile.state.TripProviderState
import kotlinx.coroutines.launch

class TripProviderViewModel(
    private val getTripProviderDetailsUseCase: GetTripProviderDetailsUseCase,
    val getUpcomingTripByProviderUseCase: GetUpcomingTripByProviderUseCase
) :
    GenericViewModel<TripProviderState, TripProviderViewModel.TripProviderEvent>(TripProviderState()) {


    fun handlerEvent(event: TripProviderEvent) {
        viewModelScope.launch {
            when (event) {
                is TripProviderEvent.TripProviderDetailsLoading -> {
                    setState { copy(isLoading = true) }
                    getDetails(event.id)
                    if (state.value.tripProviderModel != null) {
                        getUpcomingTrips(event.id)
                    }
                    setState { copy(isLoading = false) }
                }
            }
        }
    }

    private suspend fun getDetails(id: String) {
        when (val result = getTripProviderDetailsUseCase(id)) {
            is OperationResult.Error -> {
            }

            is OperationResult.Success -> {
                updateState { copy(tripProviderModel = result.data) }
            }
        }
    }

    private suspend fun getUpcomingTrips(id: String) {
        when (val result = getUpcomingTripByProviderUseCase(id)) {
            is OperationResult.Error -> {
            }

            is OperationResult.Success -> {
                updateState { copy(upcomingTrips = result.data) }
            }
        }
    }


    sealed interface TripProviderEvent {
        class TripProviderDetailsLoading(val id: String) : TripProviderEvent
    }

}