package com.agusteam.traveller.data.network.services

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.mappers.mapResponse
import com.agusteam.traveller.data.model.BusinessProfileModel
import com.agusteam.traveller.data.model.TripProviderRequest
import com.agusteam.traveller.data.model.TripProviderUpcomingTripsResponseItem
import com.agusteam.traveller.data.model.TripWishListResponse
import com.agusteam.traveller.presenter.URL
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class TripProviderService(
    private val httpClient: HttpClient
) {
    suspend fun getBusinessProfile(request: TripProviderRequest): OperationResult<BusinessProfileModel> {
        return try {
            val response = httpClient.post(
                urlString = "${URL}businessProfile"
            ) {
                setBody(request)
                contentType(ContentType.Application.Json) // Ensure the Content-Type is set
            }
            return mapResponse<BusinessProfileModel>(response)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }


    suspend fun getUpcomingTripsByProvider(providerId: String): OperationResult<List<TripProviderUpcomingTripsResponseItem>> {
        return try {
            val response = httpClient.get(
                urlString = "${URL}getBusinessUpcomingTrips/$providerId" // Use string interpolation to insert the trip
            ) {
                contentType(ContentType.Application.Json) // Ensure the Content-Type is set
            }
            mapResponse<List<TripProviderUpcomingTripsResponseItem>>(response)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    suspend fun getFavoriteTripList(userId: String): OperationResult<List<TripWishListResponse>> {
        return try {
            val response = httpClient.get(
                urlString = "${URL}trip/favorite/$userId" // Use string interpolation to insert the trip
            ) {
                contentType(ContentType.Application.Json) // Ensure the Content-Type is set
            }
            mapResponse<List<TripWishListResponse>>(response)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}