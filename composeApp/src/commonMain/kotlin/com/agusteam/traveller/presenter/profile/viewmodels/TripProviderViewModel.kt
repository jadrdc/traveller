package com.agusteam.traveller.presenter.profile.viewmodels

import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.core.base.GenericViewModel
import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.usecase.GetTripProviderDetailsUseCase
import com.agusteam.traveller.presenter.profile.state.TripProviderState
import kotlinx.coroutines.launch

class TripProviderViewModel(private val getTripProviderDetailsUseCase: GetTripProviderDetailsUseCase) :
    GenericViewModel<TripProviderState, TripProviderViewModel.TripProviderEvent>(TripProviderState()) {


    fun handlerEvent(event: TripProviderEvent) {
        viewModelScope.launch {
            when (event) {
                is TripProviderEvent.TripProviderDetailsLoading -> {
                    getDetails(event.id)
                }
            }
        }
    }

    private suspend fun getDetails(id: String) {
        setState { copy(isLoading = true) }
        when (val result = getTripProviderDetailsUseCase(id)) {
            is OperationResult.Error -> {
                val e = result.exception
            }

            is OperationResult.Success -> {
                updateState { copy(tripProviderModel = result.data) }
            }
        }
        setState { copy(isLoading = false) }
    }


    sealed interface TripProviderEvent {
        class TripProviderDetailsLoading(val id: String) : TripProviderEvent
    }

}