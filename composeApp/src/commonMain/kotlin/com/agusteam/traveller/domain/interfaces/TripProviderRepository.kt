package com.agusteam.traveller.domain.interfaces

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.model.TripProviderUpcomingTripsResponseItem
import com.agusteam.traveller.data.model.TripWishListResponse
import com.agusteam.traveller.domain.models.TripFavoriteModel
import com.agusteam.traveller.domain.models.TripModel
import com.agusteam.traveller.domain.models.TripProviderModel

interface TripProviderRepository {
    suspend fun getTripProviderDetails(id: String): OperationResult<TripProviderModel>
    suspend fun getUpcomingTripsByProvider(id: String): OperationResult<List<TripModel>>
    suspend fun getFavoriteTripList(userId: String): OperationResult<List<TripModel>>
}