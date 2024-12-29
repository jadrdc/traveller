package com.agusteam.traveller.data.imp

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.mappers.toDomainModel
import com.agusteam.traveller.data.model.LoginRequest
import com.agusteam.traveller.data.model.RequestPasswordChangeModel
import com.agusteam.traveller.data.model.UserSignUpRequest
import com.agusteam.traveller.data.network.services.SignUpService
import com.agusteam.traveller.domain.interfaces.LoginRepository
import com.agusteam.traveller.domain.models.LoginModel

class LoginRepositoryImpl(private val service: SignUpService) : LoginRepository {

    override suspend fun login(email: String, password: String): OperationResult<LoginModel> {
        return try {
            when (val result = service.login(LoginRequest(email, password))) {
                is OperationResult.Success -> {
                    val model = result.data.toDomainModel()
                    OperationResult.Success(model)
                }

                is OperationResult.Error -> result
            }
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun signUpUser(
        name: String,
        lastName: String,
        phone: String,
        email: String,
        password: String
    ): OperationResult<String> {
        return try {
            when (val result =
                service.signUp(
                    UserSignUpRequest(
                        name = name,
                        lastname = lastName,
                        email = email,
                        phone = phone,
                        password = password
                    )
                )) {
                is OperationResult.Success -> {
                    OperationResult.Success(result.data)
                }

                is OperationResult.Error -> result
            }

        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun requestPasswordChangeEmail(email: String): OperationResult<String> {
        return try {
            when (val result = service.resetPasswordForEmail(RequestPasswordChangeModel(email))) {
                is OperationResult.Success -> {
                    OperationResult.Success(result.data)
                }

                is OperationResult.Error -> result
            }

        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}