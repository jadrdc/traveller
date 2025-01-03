package com.agusteam.traveller.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val id: String,
    val description: String,
    val image: String? = null,
    val is_active: Boolean
)
