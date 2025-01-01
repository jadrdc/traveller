package com.agusteam.traveller.presenter.shopping.viewmodels

import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.core.base.GenericViewModel
import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.models.ErrorModel
import com.agusteam.traveller.domain.models.PaymentModel
import com.agusteam.traveller.domain.usecase.GetTripsIncludedServicesUseCase
import com.agusteam.traveller.domain.usecase.MarkFavoriteTripUseCase
import com.agusteam.traveller.domain.usecase.UnmarkedFavoriteTripUseCase
import com.agusteam.traveller.presenter.home.navigation.TripDetailScreenRoute
import com.agusteam.traveller.presenter.shopping.state.TripDetailState
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class ShoppingItemDetailsViewModel(
    val getTripsIncludedServicesUseCase: GetTripsIncludedServicesUseCase,
    val markFavoriteTripUseCase: MarkFavoriteTripUseCase,
    val unmarkedFavoriteTripUseCase: UnmarkedFavoriteTripUseCase
) : GenericViewModel<TripDetailState, ShoppingItemDetailsViewModel.ShoppingDetailEvent>(
    TripDetailState()
) {
    fun loadShoppingDetails(model: TripDetailScreenRoute) {
        viewModelScope.launch {
            try {
                handleEvent(
                    ShoppingDetailEvent.ShoppingDetailLoaded(
                        isFavorite = model.isFavorite,
                        name = model.name,
                        month = model.month,
                        businessImage = model.businessImage,
                        businessId = model.businessId,
                        businessName = model.businessName,
                        description = model.description,
                        lat = model.lat.toDouble(),
                        lng = model.lng.toDouble(),
                        tripId = model.tripId,
                        userId = model.userdId,
                        images = model.images,
                        cancellationPolicy = model.cancellationPolicy,
                        initialPrice = model.initialPayment,
                        meetingPoint = model.meetingPoint,
                        leavingTime = model.leavingTime,
                        arrivingTime = model.arrivingTime,
                        price = model.price,
                        destiny = model.destiny
                    )
                )
            } catch (e: CancellationException) {
                val es = e.message
            }
        }
    }

    private suspend fun getIncludedServices() {
        if (state.value.tripId.isNotBlank()) {
            setState { copy(isLoadingContent = true) }
            when (val result = getTripsIncludedServicesUseCase(state.value.tripId)) {
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

    fun handleEvent(event: ShoppingDetailEvent) {
        viewModelScope.launch {
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
                    setState {
                        copy(
                            galleryPhotos = event.images,
                            userId = event.userId,
                            tripId = event.tripId,
                            isMarkedAsFavorite = event.isFavorite,
                            itemProviderState = itemProviderState.copy(
                                month = event.month,
                                businessName = event.businessName,
                                businessImage = event.businessImage
                            ),
                            businessId = event.businessId,
                            lat = event.lat,
                            lng = event.lng,
                            description = event.description,
                            title = event.name,
                            cancellationPolicy = event.cancellationPolicy,
                            destiny = event.destiny,
                            arrivingTime = event.arrivingTime,
                            leavingTime = event.leavingTime,
                            meetingPoint = event.meetingPoint,
                            initialPayment = event.initialPrice,
                            totalPayment = event.price
                        )
                    }
                    getIncludedServices()
                }

            }
        }
    }

    private suspend fun markTripFavorite() {
        setState { copy(isLoading = true) }
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
                setState {
                    copy(
                        isMarkedAsFavorite = markState
                    )
                }
            }
        }
        setState { copy(isLoading = false) }
    }

    private suspend fun changePaymentModel(paymentModel: PaymentModel) {
        setState {
            copy(
                selectedPaymentType = paymentModel
            )
        }
    }

    private suspend fun onErrorHappened(value: Boolean, title: String = "", message: String = "") {
        val errorModel = if (!value) {
            null
        } else {
            ErrorModel(title = title, message = message)
        }
        setState {
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
            val userId: String,
            val images: List<String>,
            val cancellationPolicy: String,

            val arrivingTime: String = "",
            val leavingTime: String = "",
            val meetingPoint: String = "",
            val destiny: String = "",
            val price: Int,
            val initialPrice: Int = 0,
        ) : ShoppingDetailEvent

        data object MarkFavorite : ShoppingDetailEvent
        data object OnErrorModalAccepted : ShoppingDetailEvent
        class OnPaymentTypePicked(val paymentModel: PaymentModel) : ShoppingDetailEvent
    }

}
