package com.agusteam.traveller.domain.usecase

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.model.TripListPaginationResponseItem
import com.agusteam.traveller.domain.interfaces.TripRepository

class GetPaginatedTripsUseCase(private val repository: TripRepository) {
    suspend operator fun invoke(): OperationResult<List<TripListPaginationResponseItem>> {
        return repository.getPaginatedTrips()
    }
}