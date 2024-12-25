package com.agusteam.traveller.domain.models

data class CategoryModel(
    val description: String,
    var isSelected: Boolean = false,
    val imageUrl: String? = null,
)