package com.agusteam.traveller.domain.usecase

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.interfaces.TripProviderRepository
import com.agusteam.traveller.domain.models.TripModel

class GetUpcomingTripByProviderUseCase(val repository: TripProviderRepository) {

    suspend operator fun invoke(providerId: String): OperationResult<List<TripModel>> {
        return repository.getUpcomingTripsByProvider(providerId)
    }
}