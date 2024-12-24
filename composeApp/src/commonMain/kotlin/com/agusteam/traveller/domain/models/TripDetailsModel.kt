package com.agusteam.traveller.domain.models

data class TripDetailsModel(
    val description: String = "",
    val arrivingTime: String = "",
    val leavingTime: String = "",
    val meetingPoint: String = "",
    val destiny: String = "",
    val includedServices: List<String> = listOf(),
    val galleryPhotos: List<String> = listOf(
        "https://picsum.photos/200/300",
        "https://picsum.photos/300/300",
        "https://picsum.photos/400/300",
        "https://picsum.photos/500/300"
    )
)
