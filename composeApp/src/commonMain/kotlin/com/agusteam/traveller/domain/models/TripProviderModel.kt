package com.agusteam.traveller.domain.models

data class TripProviderModel(
    val id: String,
    val name: String,
    val image: String,
    val registeredItems: String,
    val currentItems: String,
    val description: String,
    val phone: String,
    val email: String,
    val address: String,
    val month: Int,
    val totalOfferedTrips: Int,

    val avatarUrl: String = "",
    val categoryModel: List<CategoryModel>,
)
