package com.agusteam.traveller.data.network.services

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.mappers.mapResponse
import com.agusteam.traveller.data.model.BusinessProfileModel
import com.agusteam.traveller.data.model.TripProviderRequest
import io.ktor.client.HttpClient
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
                urlString = "http://10.0.2.2:9000/businessProfile"
            ) {
                setBody(request)
                contentType(ContentType.Application.Json) // Ensure the Content-Type is set
            }
            return mapResponse<BusinessProfileModel>(response)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}