package com.agusteam.traveller.data.network.services

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.mappers.mapResponse
import com.agusteam.traveller.data.model.BusinessProfileModel
import com.agusteam.traveller.data.model.TripsPaginatedResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class TripService(
    private val httpClient: HttpClient
) {
    suspend fun getTrips(): OperationResult<List<TripsPaginatedResponse>> {
        return try {
            val response = httpClient.get(
                urlString = "http://10.0.2.2:9000/trip"
            ) {
                contentType(ContentType.Application.Json) // Ensure the Content-Type is set
            }
            return mapResponse<List<TripsPaginatedResponse>>(response)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}