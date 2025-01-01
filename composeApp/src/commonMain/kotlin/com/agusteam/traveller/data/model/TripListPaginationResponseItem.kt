package com.agusteam.traveller.data.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class TripListPaginationResponseItem(
    val initial_payment: Int,
    val leaving_time: Instant,
    val meeting_point: String,
    val returning_time: Instant,
    val total_payment: Int,
    val tripModel: TripModel
)