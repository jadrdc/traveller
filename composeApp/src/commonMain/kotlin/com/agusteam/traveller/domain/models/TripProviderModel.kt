package com.agusteam.traveller.domain.models

import com.agusteam.traveller.domain.models.CategoryModel
import com.agusteam.traveller.domain.models.TripModel

data class TripProviderModel(
    val name: String, val startingPoint: String, val avatarUrl: String,
    val registeredItems: String,
    val currentItems: String,
    val description: String,
    val phone:String,
    val email:String,
    val address:String,
    val categoryModel: List<CategoryModel>,
    val upcomingTrips:List<TripModel>
)
