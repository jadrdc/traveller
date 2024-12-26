package com.agusteam.traveller.domain.interfaces

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.model.TripsPaginatedResponse

interface TripRepository {
    suspend fun getPaginatedTrips(): OperationResult<List<TripsPaginatedResponse>>
}