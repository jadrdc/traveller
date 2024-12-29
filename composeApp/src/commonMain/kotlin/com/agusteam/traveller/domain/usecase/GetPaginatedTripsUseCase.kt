package com.agusteam.traveller.domain.usecase

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.model.TripsPaginatedResponse
import com.agusteam.traveller.domain.interfaces.TripRepository

class GetPaginatedTripsUseCase(private val repository: TripRepository) {
    suspend operator fun invoke(): OperationResult<List<TripsPaginatedResponse>> {
        return repository.getPaginatedTrips()
    }
}