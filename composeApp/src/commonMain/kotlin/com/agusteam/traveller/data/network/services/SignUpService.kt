package com.agusteam.traveller.data.network.services

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.mappers.mapResponse
import com.agusteam.traveller.data.model.LoginRequest
import com.agusteam.traveller.data.model.LoginResponse
import com.agusteam.traveller.data.model.RequestPasswordChangeModel
import com.agusteam.traveller.data.model.UserSignUpRequest
import com.agusteam.traveller.presenter.URL
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class SignUpService(
    private val httpClient: HttpClient
) {
    suspend fun login(model: LoginRequest): OperationResult<LoginResponse> {
        return try {
            val response = httpClient.post(
                urlString = "${URL}login"
            ) {
                contentType(ContentType.Application.Json) // Ensure the Content-Type is set
                setBody(model)
            }
            return mapResponse<LoginResponse>(response)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    suspend fun signUp(model: UserSignUpRequest): OperationResult<String> {
        return try {
            val response = httpClient.post(
                urlString = "${URL}signup"
            ) {
                contentType(ContentType.Application.Json) // Ensure the Content-Type is set
                setBody(model)
            }
            return mapResponse<String>(response)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    suspend fun resetPasswordForEmail(model: RequestPasswordChangeModel): OperationResult<String> {
        return try {
            val response = httpClient.post(
                urlString = "${URL}resetPasswordForEmail"
            ) {
                contentType(ContentType.Application.Json) // Ensure the Content-Type is set
                setBody(model)
            }
            return mapResponse<String>(response)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

}

