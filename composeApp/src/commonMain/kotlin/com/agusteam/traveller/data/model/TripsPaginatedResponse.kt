package com.agusteam.traveller.data.model

import kotlinx.serialization.Serializable
@Serializable
data class TripsPaginatedResponse(
    val businessModel: SummaryBusinessModel,
    val description: String,
    val destiny: String,
    val lat: Double,
    val lng: Double,
    val id: String,
    val name: String
)