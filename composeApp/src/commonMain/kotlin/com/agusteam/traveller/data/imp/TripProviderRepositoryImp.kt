package com.agusteam.traveller.data.imp

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.mappers.toDomain
import com.agusteam.traveller.data.model.TripProviderRequest
import com.agusteam.traveller.data.network.services.TripProviderService
import com.agusteam.traveller.domain.interfaces.TripProviderRepository
import com.agusteam.traveller.domain.models.TripProviderModel

class TripProviderRepositoryImp(private val service: TripProviderService):TripProviderRepository {
    override suspend fun getTripProviderDetails(id:String): OperationResult<TripProviderModel> {
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
        }    }
}