package com.agusteam.traveller.data.network.services

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.mappers.mapResponse
import com.agusteam.traveller.data.model.TripFavoriteRequest
import com.agusteam.traveller.data.model.TripListPaginationResponseItem
import com.agusteam.traveller.presenter.URL
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class TripService(
    private val httpClient: HttpClient
) {

    suspend fun markAsFavorite(model: TripFavoriteRequest): OperationResult<Boolean> {
        return try {
            val response = httpClient.post(
                urlString = "${URL}trip/favorite"
            ) {
                setBody(model)
                contentType(ContentType.Application.Json) // Ensure the Content-Type is set
            }
            mapResponse<Boolean>(response)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    suspend fun getTripsIncludeServices(trip: String): OperationResult<List<String>> {
        return try {
            val response = httpClient.get(
                urlString = "${URL}trip/included/$trip" // Use string interpolation to insert the trip
            ) {
                contentType(ContentType.Application.Json) // Ensure the Content-Type is set
            }
            mapResponse<List<String>>(response)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    suspend fun unmarkAsFavorite(model: TripFavoriteRequest): OperationResult<Boolean> {
        return try {
            val response = httpClient.delete(
                urlString = "${URL}trip/favorite"
            ) {
                setBody(model)
                contentType(ContentType.Application.Json) // Ensure the Content-Type is set
            }
            mapResponse<Boolean>(response)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    suspend fun getTrips(): OperationResult<List<TripListPaginationResponseItem>> {
        return try {
            val response = httpClient.get(
                urlString = "${URL}trip/availables"
            ) {
                contentType(ContentType.Application.Json) // Ensure the Content-Type is set
            }
            return mapResponse<List<TripListPaginationResponseItem>>(response)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}