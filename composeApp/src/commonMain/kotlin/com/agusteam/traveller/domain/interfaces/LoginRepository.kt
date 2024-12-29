package com.agusteam.traveller.domain.interfaces

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.models.LoginModel

interface LoginRepository {
    suspend fun login(email: String, password: String): OperationResult<LoginModel>
    suspend fun requestPasswordChangeEmail(email: String): OperationResult<String>
    suspend fun signUpUser(
        name: String,
        lastName: String,
        phone: String,
        email: String,
        password: String
    ): OperationResult<String>

}