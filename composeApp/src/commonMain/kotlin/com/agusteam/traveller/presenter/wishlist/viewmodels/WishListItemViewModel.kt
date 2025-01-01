package com.agusteam.traveller.presenter.wishlist.viewmodels

import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.core.base.GenericViewModel
import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.usecase.GetTripFavoriteListUseCase
import com.agusteam.traveller.presenter.wishlist.state.WishListState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WishListItemViewModel(val getTripFavoriteListUseCase: GetTripFavoriteListUseCase) :
    GenericViewModel<WishListState, WishListItemViewModel.WishListEvent>(WishListState()) {

    fun handleEvent(event: WishListEvent) {
        viewModelScope.launch {
            when (event) {
                is WishListEvent.WishListLoad -> {
                    getFavoriteTrips(event.userId)
                }
            }
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
        data class WishListLoad(val userId: String) : WishListEvent
    }
}