package com.agusteam.traveller.domain.usecase

import androidx.datastore.preferences.core.Preferences
import com.agusteam.traveller.domain.interfaces.LocalStoragePreferenceRepository
import kotlinx.coroutines.flow.Flow

class GetProfileUseCase(private val repository: LocalStoragePreferenceRepository) {

    suspend operator fun invoke(): Flow<Preferences> {
        return repository.getValue()
    }
}