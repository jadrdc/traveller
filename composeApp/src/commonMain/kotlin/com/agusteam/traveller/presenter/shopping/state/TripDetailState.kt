package com.agusteam.traveller.presenter.shopping.state

import com.agusteam.traveller.core.base.ViewModelState
import com.agusteam.traveller.domain.models.ErrorModel
import com.agusteam.traveller.domain.models.PaymentModel
import com.agusteam.traveller.domain.models.TripProviderModel
import com.agusteam.traveller.domain.models.TripDetailsModel
import com.agusteam.traveller.presenter.INITIAL_PAYMENT
import com.agusteam.traveller.presenter.TOTAL_PAYMENT
import com.agusteam.traveller.presenter.getProvider

data class TripDetailState(
    val title: String = "",
    val description: String = "",
    val destiny: String = "",
    val businessId: String = "",
    val businessImage: String = "",
    val businessName: String = "",
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val avatarUrl: String = "",
    val month: Int = 0,
    val isMarkedAsFavorite: Boolean = false,
    val errorModel: ErrorModel? = null,
    val userId: String = "",
    val tripId: String = "",
    val includedServices: List<String> = listOf(),
    val isLoading: Boolean = false,
    val isLoadingContent: Boolean = false,
    val galleryPhotos: List<String> = listOf(),
    val cancellationPolicy: String = "",

    val tripProviderModel: TripProviderModel = getProvider(),
    val initialPayment: String = INITIAL_PAYMENT,
    val totalPayment: String = TOTAL_PAYMENT,
    val selectedPaymentType: PaymentModel = PaymentModel.TOTAL_PAYMENT,
    val details: TripDetailsModel = TripDetailsModel()
) : ViewModelState

