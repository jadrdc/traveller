package com.agusteam.traveller.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TripModelSummary(
    val destiny: String,
    val id: String,
    val images: List<String>,
    val name: String
)