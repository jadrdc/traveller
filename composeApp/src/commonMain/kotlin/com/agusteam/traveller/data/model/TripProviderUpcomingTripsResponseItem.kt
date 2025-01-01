package com.agusteam.traveller.data.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class TripProviderUpcomingTripsResponseItem(
    val leaving_time: Instant,
    val returning_time: String,
    val total_payment: Int,
    val tripModel: TripModelSummary
)