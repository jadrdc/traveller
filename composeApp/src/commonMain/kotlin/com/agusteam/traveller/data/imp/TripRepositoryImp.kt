package com.agusteam.traveller.data.imp

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.model.TripFavoriteRequest
import com.agusteam.traveller.data.model.TripsPaginatedResponse
import com.agusteam.traveller.data.network.services.TripService
import com.agusteam.traveller.domain.interfaces.TripRepository

class TripRepositoryImp(private val service: TripService) : TripRepository {

    override suspend fun getTripsIncludedServices(tripId: String): OperationResult<List<String>> {
        return try {
            when (val result = service.getTripsIncludeServices(tripId)) {
                is OperationResult.Success -> {
                    val model = result.data
                    OperationResult.Success(model)
                }

                is OperationResult.Error -> result
            }
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

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

    override suspend fun markFavorite(userdId: String, tripId: String): OperationResult<Boolean> {
        return try {
            when (val result =
                service.markAsFavorite(TripFavoriteRequest(user_id = userdId, trip_id = tripId))) {
                is OperationResult.Success -> {
                    OperationResult.Success(result.data)
                }

                is OperationResult.Error -> result
            }
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun unmarkAsFavorite(
        userdId: String,
        tripId: String
    ): OperationResult<Boolean> {
        return try {
            when (val result =
                service.unmarkAsFavorite(
                    TripFavoriteRequest(
                        user_id = userdId,
                        trip_id = tripId
                    )
                )) {
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