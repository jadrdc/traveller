package com.agusteam.traveller.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class CategoryModel(
    val description: String,
    var isSelected: Boolean = false,
    val imageUrl: String? = null,
)