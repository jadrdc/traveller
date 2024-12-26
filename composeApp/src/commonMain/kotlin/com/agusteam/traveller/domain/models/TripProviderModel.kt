package com.agusteam.traveller.domain.models

import com.agusteam.traveller.presenter.SAMPLE_ID_TRIP
import kotlin.math.roundToInt

data class TripProviderModel(
    val id: String = SAMPLE_ID_TRIP,
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
    val categoryModel: List<CategoryModel>,
    val upcomingTrips: List<TripModel>
) {
    fun getTimePeriodUnit(): String {
        val result = if (month in 0..11) {
            month.toDouble()  // Convert month to Double for fractional support
        } else {
            month / 12.0  // Divide by 12 to get the number of years as a double
        }
        val roundedResult = (result * 10.0).roundToInt() / 10.0

        return roundedResult.toString()  // Format the result to 1 decimal point
    }


    fun getTimePeriod(): String {
        return if (month == 0 || month in 2..11) {
            "Meses"
        } else if (month == 1) {
            "Mes"
        } else if (month == 12) {
            "Año"
        } else if (month > 12) {
            "Años"
        } else {
            ""
        }
    }
}
