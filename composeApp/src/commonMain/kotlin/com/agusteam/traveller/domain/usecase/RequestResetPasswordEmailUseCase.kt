package com.agusteam.traveller.domain.usecase

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.interfaces.LoginRepository

class RequestResetPasswordEmailUseCase(private val repository: LoginRepository) {
    suspend operator fun invoke(email:String): OperationResult<String> {
        return repository.requestPasswordChangeEmail(email)
    }
}