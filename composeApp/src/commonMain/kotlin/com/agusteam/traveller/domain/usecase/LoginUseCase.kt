package com.agusteam.traveller.domain.usecase

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.interfaces.LoginRepository
import com.agusteam.traveller.domain.models.LoginModel

class LoginUseCase(private val repository: LoginRepository) {
    suspend operator fun invoke(username: String, password: String): OperationResult<LoginModel> {
        return repository.login(username, password)
    }
}