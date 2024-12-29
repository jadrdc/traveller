package com.agusteam.traveller.domain.usecase

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.interfaces.LoginRepository

class SignUpUseCase(private val repository: LoginRepository) {
    suspend operator fun invoke(
        name: String,
        lastName: String,
        phone: String,
        email: String,
        password: String
    ): OperationResult<String> {
        return repository.signUpUser(name, lastName, phone, email, password)
    }
}