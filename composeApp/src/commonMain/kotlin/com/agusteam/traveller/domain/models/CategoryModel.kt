package com.agusteam.traveller.domain.models

import org.jetbrains.compose.resources.DrawableResource

data class CategoryModel(
    val description: String,
    var isSelected: Boolean = false,
    val imageIcon: DrawableResource,
)