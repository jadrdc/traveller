package com.agusteam.traveller.domain.models

data class TripModel(
    val name: String,
    val image: String = "https://picsum.photos/200/300",
    val price: String = "2,430",
    val destiny: String = "",
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val description: String = "",
    val date: String = "Aug 31 - Sep 5",
    val categoryList: List<CategoryModel> = listOf(),
    val isSavedForLater: Boolean = false
)
