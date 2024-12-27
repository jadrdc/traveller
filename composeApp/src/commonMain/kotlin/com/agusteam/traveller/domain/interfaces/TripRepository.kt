package com.agusteam.traveller.domain.interfaces

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.model.TripsPaginatedResponse

interface TripRepository {
    suspend fun getPaginatedTrips(): OperationResult<List<TripsPaginatedResponse>>
    suspend fun markFavorite(userdId: String, tripId: String): OperationResult<Boolean>
    suspend fun getTripsIncludedServices(tripId: String): OperationResult<List<String>>
    suspend fun unmarkAsFavorite(userdId: String, tripId: String): OperationResult<Boolean>
}