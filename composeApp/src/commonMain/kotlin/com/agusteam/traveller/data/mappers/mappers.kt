package com.agusteam.traveller.data.mappers

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.model.BusinessProfileModel
import com.agusteam.traveller.data.model.CategoryResponse
import com.agusteam.traveller.data.model.ErrorResponse
import com.agusteam.traveller.data.model.LoginResponse
import com.agusteam.traveller.data.model.TripProviderUpcomingTripsResponseItem
import com.agusteam.traveller.data.model.TripWishListResponse
import com.agusteam.traveller.domain.models.CategoryModel
import com.agusteam.traveller.domain.models.LoginModel
import com.agusteam.traveller.domain.models.TripModel
import com.agusteam.traveller.domain.models.TripProviderModel
import com.agusteam.traveller.presenter.formatInstant
import com.agusteam.traveller.presenter.formatPhone
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

fun TripWishListResponse.toDomain(): TripModel {
    return TripModel(
        date = tripModel.leavingTime,
        id = tripModel.id,
        name = tripModel.name,
        images = tripModel.images,
        destiny = tripModel.destiny,
        lat = tripModel.lat,
        cancellation_policy = tripModel.cancellation_policy,
        lng = tripModel.lng,
        description = tripModel.description,
        arrivingTime = tripModel.arrivingTime,
        leavingTime = tripModel.leavingTime,
        price = tripModel.price,
        month = tripModel.businessModel?.month ?: 0,
        businessImage = tripModel.businessModel?.image ?: "",
        businessId = tripModel.businessModel?.id ?: "",
        businessName = tripModel.businessModel?.name ?: ""
    )
}

fun List<TripProviderUpcomingTripsResponseItem>.toDomain(): List<TripModel> {
    return map {
        TripModel(
            date = formatInstant(it.leaving_time),
            id = it.tripModel.id,
            name = it.tripModel.name,
            images = it.tripModel.images,
            destiny = it.tripModel.destiny,
            arrivingTime = it.returning_time,
            leavingTime = formatInstant(it.leaving_time),
            price = it.total_payment
        )
    }
}

fun LoginResponse.toDomainModel(): LoginModel {
    return LoginModel(email, id, lastname, name, phone)
}

fun CategoryResponse.toDomainModel(): CategoryModel {
    return CategoryModel(description = description, isSelected = false, imageUrl = image)
}

fun BusinessProfileModel.toDomain(): TripProviderModel {
    return TripProviderModel(
        address = address,
        phone = formatPhone(phone),
        email = email,
        categoryModel = categories.map {
            CategoryModel(imageUrl = it.image, description = it.description)
        }, id = id,
        name = name,
        avatarUrl = image,
        registeredItems = "0",
        month = month,
        currentItems = "0",
        description = description, image = image, totalOfferedTrips = tripOffers
    )
}

suspend inline fun <reified T> mapResponse(response: HttpResponse): OperationResult<T> {
    return when (response.status.value) {
        in 200..299 -> {
            // Map the response body to the expected type
            val body = response.body<T>()
            OperationResult.Success(body)
        }

        else -> {
            // Handle error response and map it to the ErrorResponse type
            val error = response.body<ErrorResponse>()
            OperationResult.Error(Exception(error.message))
        }
    }
}
