package com.agusteam.traveller.presenter.shopping.viewmodels

import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.core.base.GenericViewModel
import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.models.ErrorModel
import com.agusteam.traveller.domain.models.PaymentModel
import com.agusteam.traveller.domain.usecase.GetTripsIncludedServicesUseCase
import com.agusteam.traveller.domain.usecase.MarkFavoriteTripUseCase
import com.agusteam.traveller.domain.usecase.UnmarkedFavoriteTripUseCase
import com.agusteam.traveller.presenter.getShoppingItemsDetails
import com.agusteam.traveller.presenter.shopping.state.TripDetailState
import kotlinx.coroutines.launch

class ShoppingItemDetailsViewModel(
    val getTripsIncludedServicesUseCase: GetTripsIncludedServicesUseCase,
    val markFavoriteTripUseCase: MarkFavoriteTripUseCase,
    val unmarkedFavoriteTripUseCase: UnmarkedFavoriteTripUseCase
) : GenericViewModel<TripDetailState, ShoppingItemDetailsViewModel.ShoppingDetailEvent>(
    TripDetailState()
) {

    private fun getIncludedServices() {
        if (state.value.tripId.isNotBlank()) {
            viewModelScope.launch {
                updateState { copy(isLoadingContent = true) }
                when (val result = getTripsIncludedServicesUseCase(state.value.tripId)) {
                    is OperationResult.Success -> {
                        updateState { copy(includedServices = result.data) }
                    }

                    is OperationResult.Error -> {
                        val e = result.exception
                    }
                }
                updateState { copy(isLoadingContent = false) }
            }
        }
    }

    fun handleEvent(event: ShoppingDetailEvent) {
        when (event) {
            is ShoppingDetailEvent.OnPaymentTypePicked -> {
                changePaymentModel(event.paymentModel)
            }

            is ShoppingDetailEvent.MarkFavorite -> {
                markTripFavorite()
            }

            is ShoppingDetailEvent.OnErrorModalAccepted -> {
                onErrorHappened(false)
            }

            is ShoppingDetailEvent.ShoppingDetailLoaded -> {
                updateState {
                    copy(
                        userId = event.userId,
                        tripId = event.tripId,
                        isMarkedAsFavorite = event.isFavorite,
                        month = event.month,
                        businessImage = event.businessImage,
                        businessId = event.businessId,
                        businessName = event.businessName,
                        lat = event.lat,
                        lng = event.lng,
                        description = event.description,
                        details = getShoppingItemsDetails(),
                        title = event.name,
                    )
                }
                getIncludedServices()
            }
        }
    }

    private fun markTripFavorite() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            val markState = !state.value.isMarkedAsFavorite
            val result = if (markState) {
                markFavoriteTripUseCase(userId = state.value.userId, tripId = state.value.tripId)
            } else {
                unmarkedFavoriteTripUseCase(
                    userId = state.value.userId,
                    tripId = state.value.tripId
                )
            }
            when (result) {
                is OperationResult.Error -> {
                    onErrorHappened(
                        true,
                        "Error cambiando el estado de viaje",
                        "No se pudo completar la operacion,intente mas tarde."
                    )
                }

                is OperationResult.Success -> {
                    updateState {
                        copy(
                            isMarkedAsFavorite = markState
                        )
                    }
                }
            }
            updateState { copy(isLoading = false) }
        }
    }

    private fun changePaymentModel(paymentModel: PaymentModel) {
        updateState {
            copy(
                selectedPaymentType = paymentModel
            )
        }
    }

    private fun onErrorHappened(value: Boolean, title: String = "", message: String = "") {
        val errorModel = if (!value) {
            null
        } else {
            ErrorModel(title = title, message = message)
        }
        updateState {
            copy(
                errorModel = errorModel
            )
        }
    }

    sealed interface ShoppingDetailEvent {
        class ShoppingDetailLoaded(
            val tripId: String,
            val name: String,
            val lat: Double,
            val lng: Double,
            val businessId: String,
            val businessImage: String,
            val businessName: String,
            val description: String,
            val month: Int,
            val isFavorite: Boolean,
            val userId: String

        ) : ShoppingDetailEvent

        data object MarkFavorite : ShoppingDetailEvent
        data object OnErrorModalAccepted : ShoppingDetailEvent
        class OnPaymentTypePicked(val paymentModel: PaymentModel) : ShoppingDetailEvent
    }

}
