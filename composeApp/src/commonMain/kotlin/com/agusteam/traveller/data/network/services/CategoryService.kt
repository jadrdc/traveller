package com.agusteam.traveller.data.network.services

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.mappers.mapResponse
import com.agusteam.traveller.data.model.CategoryResponse
import com.agusteam.traveller.presenter.URL
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class CategoryService(
    private val httpClient: HttpClient
) {
    suspend fun getCategories(): OperationResult<List<CategoryResponse>> {
        return try {
            val response = httpClient.get(
                urlString = "${URL}categories"
            ) {
                contentType(ContentType.Application.Json) // Ensure the Content-Type is set
            }
            return mapResponse<List<CategoryResponse>>(response)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}