package com.agusteam.traveller.domain.models

import com.agusteam.traveller.presenter.getGalleryPhoto

data class TripDetailsModel(
    val description: String = "",
    val destiny: String = "",

    val arrivingTime: String = "",
    val leavingTime: String = "",
    val meetingPoint: String = "",
    val includedServices: List<String> = listOf(),
    val galleryPhotos: List<String> = getGalleryPhoto()
)
