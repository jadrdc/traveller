package com.agusteam.traveller.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val id: Int,
    val description: String,
    val image: String? = null,
    val is_active: Boolean
)
