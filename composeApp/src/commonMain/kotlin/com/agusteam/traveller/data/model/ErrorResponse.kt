package com.agusteam.traveller.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val status: Int,
    val error: String,
    val message: String
)
