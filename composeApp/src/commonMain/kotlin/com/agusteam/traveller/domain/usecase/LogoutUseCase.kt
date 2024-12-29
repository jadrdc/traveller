package com.agusteam.traveller.domain.usecase

import com.agusteam.traveller.domain.interfaces.LocalStoragePreferenceRepository

class LogoutUseCase(private val repository: LocalStoragePreferenceRepository) {
    suspend operator fun invoke() {
        return repository.clear()
    }
}