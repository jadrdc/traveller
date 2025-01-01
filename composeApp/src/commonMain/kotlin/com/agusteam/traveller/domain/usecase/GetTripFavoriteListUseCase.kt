package com.agusteam.traveller.domain.usecase

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.interfaces.TripProviderRepository
import com.agusteam.traveller.domain.models.TripFavoriteModel
import com.agusteam.traveller.domain.models.TripModel

class GetTripFavoriteListUseCase(val repository: TripProviderRepository) {

    suspend operator fun invoke(userId: String): OperationResult<List<TripModel>> {
        return repository.getFavoriteTripList(userId)
    }
}