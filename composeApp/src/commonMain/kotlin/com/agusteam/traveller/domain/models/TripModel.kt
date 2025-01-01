package com.agusteam.traveller.domain.models

import com.agusteam.traveller.data.model.BusinessModel
import com.agusteam.traveller.data.model.BusinessProfileModel
import com.agusteam.traveller.data.model.SummaryBusinessModel
import kotlinx.serialization.Serializable

@Serializable
data class TripModel(
    val id: String,
    val name: String,
    val destiny: String = "",
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val businessId: String = "",
    val businessImage: String = "",
    val businessName: String = "",
    val description: String = "",
    val month: Int = 0,
    val images: List<String> = listOf(),
    val cancellation_policy: String = "",
    val categoryList: List<CategoryModel> = listOf(),
    val isSavedForLater: Boolean = false,

    val arrivingTime: String = "",
    val leavingTime: String = "",
    val meetingPoint: String = "",
    val price: Int = 0,
    val initialPayment: Int = 0,
    val businessModel: SummaryBusinessModel? = null,

    val date: String = "",

    )
