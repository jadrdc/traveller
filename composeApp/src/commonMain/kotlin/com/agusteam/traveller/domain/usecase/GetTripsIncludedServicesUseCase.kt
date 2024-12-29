package com.agusteam.traveller.domain.usecase

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.interfaces.TripRepository

class GetTripsIncludedServicesUseCase(val repository: TripRepository) {

    suspend operator fun invoke(tripId: String): OperationResult<List<String>> {
       return repository.getTripsIncludedServices(tripId)
    }
}