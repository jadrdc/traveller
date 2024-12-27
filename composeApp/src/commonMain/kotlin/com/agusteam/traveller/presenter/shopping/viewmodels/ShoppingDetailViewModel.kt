package com.agusteam.traveller.presenter.shopping.viewmodels

import com.agusteam.traveller.core.base.GenericViewModel
import com.agusteam.traveller.domain.models.PaymentModel
import com.agusteam.traveller.presenter.getShoppingItemsDetails
import com.agusteam.traveller.presenter.shopping.state.TripDetailState

class ShoppingitemsDetailsViewModel :
    GenericViewModel<TripDetailState, ShoppingitemsDetailsViewModel.ShoppingDetailEvent>(
        TripDetailState()
    ) {


    fun handleEvent(event: ShoppingDetailEvent) {
        when (event) {
            is ShoppingDetailEvent.OnPaymentTypePicked -> {
                changePaymentModel(event.paymentModel)
            }

            is ShoppingDetailEvent.ShoppingDetailLoaded -> {
                updateState {
                    copy(
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
            }
        }
    }

    private fun changePaymentModel(paymentModel: PaymentModel) {
        updateState {
            copy(
                selectedPaymentType = paymentModel
            )
        }
    }


    sealed interface ShoppingDetailEvent {
        class ShoppingDetailLoaded(
            val name: String,
            val lat: Double,
            val lng: Double,
            val businessId: String,
            val businessImage: String,
            val businessName: String,
            val description: String,
            val month: Int

        ) : ShoppingDetailEvent

        class OnPaymentTypePicked(val paymentModel: PaymentModel) : ShoppingDetailEvent
    }

}
