package com.agusteam.traveller.domain.interfaces

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.models.TripProviderModel

interface TripProviderRepository {
    suspend fun getTripProviderDetails(id: String): OperationResult<TripProviderModel>
}