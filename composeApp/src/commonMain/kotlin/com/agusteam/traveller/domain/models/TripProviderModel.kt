package com.agusteam.traveller.domain.models

import com.agusteam.traveller.presenter.SAMPLE_ID_TRIP_2

data class TripProviderModel(
    val id: String = SAMPLE_ID_TRIP_2,
    val name: String,
    val image: String,
    val avatarUrl: String,
    val registeredItems: String,
    val currentItems: String,
    val description: String,
    val phone: String,
    val email: String,
    val address: String,
    val month: Int,
    val categoryModel: List<CategoryModel>
)
