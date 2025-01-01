package com.agusteam.traveller.presenter.orders.viewmodels

import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.core.base.GenericViewModel
import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.usecase.GetTripsIncludedServicesUseCase
import com.agusteam.traveller.presenter.wishlist.state.WishListOrderDetailState
import kotlinx.coroutines.launch

class WishListOrderDetailViewModel(
    val getTripsIncludedServicesUseCase: GetTripsIncludedServicesUseCase,
) : GenericViewModel<WishListOrderDetailState, WishListOrderDetailViewModel.OrderDetailsEvent>(
    WishListOrderDetailState()
) {

    fun handleEvent(event: OrderDetailsEvent) {
        viewModelScope.launch {
            when (event) {
                is OrderDetailsEvent.OrderDetailsLoadIncludeServices -> {
                    setState {
                        copy(
                            itemProviderState.copy(
                                businessName = event.businessName,
                                businessImage = event.businessImage,
                                month = event.month
                            )
                        )
                    }
                    getIncludedServices(event.tripId)
                }
            }
        }
    }

    private suspend fun getIncludedServices(tripId: String) {
        if (tripId.isNotBlank()) {
            setState { copy(isLoadingContent = true) }
            when (val result = getTripsIncludedServicesUseCase(tripId)) {
                is OperationResult.Success -> {
                    updateState { copy(includedServices = result.data) }
                }

                is OperationResult.Error -> {
                    val e = result.exception
                }
            }
            setState { copy(isLoadingContent = false) }
        }
    }


    sealed interface OrderDetailsEvent {
        data class OrderDetailsLoadIncludeServices(
            val tripId: String,
            val businessName: String,
            val businessImage: String,
            val month: Int
        ) : OrderDetailsEvent {
        }
    }
}