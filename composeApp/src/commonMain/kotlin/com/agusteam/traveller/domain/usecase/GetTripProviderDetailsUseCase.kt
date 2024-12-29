package com.agusteam.traveller.domain.usecase

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.interfaces.TripProviderRepository
import com.agusteam.traveller.domain.models.TripProviderModel

class GetTripProviderDetailsUseCase(private val repository: TripProviderRepository) {
    suspend operator fun invoke(id:String): OperationResult<TripProviderModel> {
        return repository.getTripProviderDetails(id)
    }
}