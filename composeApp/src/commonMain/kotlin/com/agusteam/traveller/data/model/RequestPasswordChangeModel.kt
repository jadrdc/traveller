package com.agusteam.traveller.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RequestPasswordChangeModel(val email: String)