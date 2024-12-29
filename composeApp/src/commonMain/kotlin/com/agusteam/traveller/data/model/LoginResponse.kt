package com.agusteam.traveller.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val email: String,
    val id: String,
    val lastname: String,
    val name: String,
    val phone: String
)