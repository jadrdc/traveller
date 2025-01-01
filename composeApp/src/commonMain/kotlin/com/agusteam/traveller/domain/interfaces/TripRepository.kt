package com.agusteam.traveller.domain.interfaces

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.model.TripListPaginationResponseItem

interface TripRepository {
    suspend fun getPaginatedTrips(): OperationResult<List<TripListPaginationResponseItem>>
    suspend fun markFavorite(userdId: String, tripId: String): OperationResult<Boolean>
    suspend fun getTripsIncludedServices(tripId: String): OperationResult<List<String>>
    suspend fun unmarkAsFavorite(userdId: String, tripId: String): OperationResult<Boolean>
}