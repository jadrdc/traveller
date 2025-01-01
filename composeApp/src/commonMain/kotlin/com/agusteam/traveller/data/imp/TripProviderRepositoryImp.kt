package com.agusteam.traveller.data.imp

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.mappers.toDomain
import com.agusteam.traveller.data.model.TripProviderRequest
import com.agusteam.traveller.data.network.services.TripProviderService
import com.agusteam.traveller.domain.interfaces.TripProviderRepository
import com.agusteam.traveller.domain.models.TripModel
import com.agusteam.traveller.domain.models.TripProviderModel

class TripProviderRepositoryImp(private val service: TripProviderService) : TripProviderRepository {
    override suspend fun getTripProviderDetails(id: String): OperationResult<TripProviderModel> {
        return try {
            when (val result = service.getBusinessProfile(TripProviderRequest(id))) {
                is OperationResult.Success -> {
                    val model = result.data.toDomain()
                    OperationResult.Success(model)
                }

                is OperationResult.Error -> result
            }
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun getUpcomingTripsByProvider(id: String): OperationResult<List<TripModel>> {
        return try {
            when (val result = service.getUpcomingTripsByProvider(id)) {
                is OperationResult.Success -> {
                    OperationResult.Success(result.data.toDomain())
                }

                is OperationResult.Error -> result
            }
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun getFavoriteTripList(userId: String): OperationResult<List<TripModel>> {
        return try {
            when (val result = service.getFavoriteTripList(userId)) {
                is OperationResult.Success -> {
                    OperationResult.Success(result.data.map { it.toDomain() })
                }

                is OperationResult.Error -> result
            }
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}