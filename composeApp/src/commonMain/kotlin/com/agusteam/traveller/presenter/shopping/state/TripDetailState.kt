package com.agusteam.traveller.presenter.shopping.state

import com.agusteam.traveller.domain.models.PaymentModel
import com.agusteam.traveller.domain.models.TripProviderModel
import com.agusteam.traveller.domain.models.TripDetailsModel
import com.agusteam.traveller.presenter.getProvider

data class TripDetailState(
    val title: String = "",
    val tripId: String="",
    val tripProviderModel: TripProviderModel = getProvider(),
    val isMarkedAsFavorite: Boolean = false,
    val initialPayment: String = "$1000.0",
    val totalPayment: String = "$12000.0",
    val selectedPaymentType: PaymentModel = PaymentModel.TOTAL_PAYMENT,
    val details: TripDetailsModel = TripDetailsModel()
)

