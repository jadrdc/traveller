package com.agusteam.traveller.presenter.wishlist.viewmodels

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.core.base.GenericViewModel
import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.util.USER_ID
import com.agusteam.traveller.domain.usecase.GetProfileUseCase
import com.agusteam.traveller.domain.usecase.GetTripFavoriteListUseCase
import com.agusteam.traveller.presenter.wishlist.state.WishListOrderDetailState
import com.agusteam.traveller.presenter.wishlist.state.WishListState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch

class WishListItemViewModel(
    val getTripFavoriteListUseCase: GetTripFavoriteListUseCase,
    val getProfileUseCase: GetProfileUseCase,
    ) :
    GenericViewModel<WishListState, WishListItemViewModel.WishListEvent>(WishListState()) {

    init {
        viewModelScope.launch {
            getProfileUseCase().mapLatest { preference ->
                val userIdKey = stringPreferencesKey(USER_ID)
                val userId = preference[userIdKey] ?: ""
                getFavoriteTrips(userId)

            }.launchIn(viewModelScope)
        }
    }

    private suspend fun getFavoriteTrips(userId: String) {
        setState { copy(isLoading = true) }
        when (val result = getTripFavoriteListUseCase(userId)) {
            is OperationResult.Error -> {
                setState { copy(errorState = true) }
            }

            is OperationResult.Success -> {
                setState { copy(favoriteItems = result.data) }
            }
        }
        delay(2000)
        setState { copy(isLoading = false) }
    }


    sealed interface WishListEvent {
    }
}