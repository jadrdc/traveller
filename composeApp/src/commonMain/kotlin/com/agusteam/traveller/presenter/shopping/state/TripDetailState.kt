package com.agusteam.traveller.presenter.shopping.state

import com.agusteam.traveller.core.base.ViewModelState
import com.agusteam.traveller.domain.models.ErrorModel
import com.agusteam.traveller.domain.models.PaymentModel
import com.agusteam.traveller.domain.models.TripProviderModel
import com.agusteam.traveller.presenter.common.ItemProviderState

data class TripDetailState(
    val title: String = "",
    val description: String = "",
    val destiny: String = "",
    val businessId: String = "",
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val avatarUrl: String = "",
    val isMarkedAsFavorite: Boolean = false,
    val errorModel: ErrorModel? = null,
    val userId: String = "",
    val tripId: String = "",
    val includedServices: List<String> = listOf(),
    val isLoading: Boolean = false,
    val isLoadingContent: Boolean = false,
    val galleryPhotos: List<String> = listOf(),
    val cancellationPolicy: String = "",
    val arrivingTime: String = "",
    val leavingTime: String = "",
    val meetingPoint: String = "",
    val initialPayment: Int = 0,
    val totalPayment: Int = 0,
    val tripProviderModel: TripProviderModel? = null,
    val selectedPaymentType: PaymentModel = PaymentModel.TOTAL_PAYMENT,
    val itemProviderState: ItemProviderState = ItemProviderState("", "", 0)

    // val businessImage: String = "",
    // val businessName: String = "",
    // val month: Int = 0,

) : ViewModelState

