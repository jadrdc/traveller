package com.agusteam.traveller.domain.models

import com.agusteam.traveller.presenter.DATE_RANGE
import com.agusteam.traveller.presenter.PRICE
import com.agusteam.traveller.presenter.TRIP_IMAGE
import kotlinx.serialization.Serializable

@Serializable
data class TripModel(
    val name: String,
    val destiny: String = "",
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val businessId: String = "",
    val businessImage: String = "",
    val businessName: String = "",
    val description: String = "",
    val month: Int = 0,


    val image: String = TRIP_IMAGE,
    val price: String = PRICE,
    val date: String = DATE_RANGE,
    val categoryList: List<CategoryModel> = listOf(),
    val isSavedForLater: Boolean = false
)
