package com.agusteam.traveller.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TripFavoriteRequest(
    val user_id: String,
    val trip_id: String
)
