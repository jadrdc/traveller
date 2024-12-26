package com.agusteam.traveller.data.imp

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.model.TripsPaginatedResponse
import com.agusteam.traveller.data.network.services.TripService
import com.agusteam.traveller.domain.interfaces.TripRepository

class TripRepositoryImp(private val service: TripService) : TripRepository {
    override suspend fun getPaginatedTrips(): OperationResult<List<TripsPaginatedResponse>> {
        return try {
            when (val result = service.getTrips()) {
                is OperationResult.Success -> {
                    OperationResult.Success(result.data)
                }

                is OperationResult.Error -> result
            }
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}