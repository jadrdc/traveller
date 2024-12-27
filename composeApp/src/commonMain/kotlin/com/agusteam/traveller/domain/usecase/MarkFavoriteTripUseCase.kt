package com.agusteam.traveller.domain.usecase

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.interfaces.TripRepository

class MarkFavoriteTripUseCase(private val repository: TripRepository) {

    suspend operator fun invoke(userId: String, tripId: String): OperationResult<Boolean> {
        return repository.markFavorite(userId, tripId)
    }
}