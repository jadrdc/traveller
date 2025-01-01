package com.agusteam.traveller.data.model

import com.agusteam.traveller.domain.models.TripModel
import kotlinx.serialization.Serializable

@Serializable
data class TripWishListResponse(val tripModel: TripModel)
