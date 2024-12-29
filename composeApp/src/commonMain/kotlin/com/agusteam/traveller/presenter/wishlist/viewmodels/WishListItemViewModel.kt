package com.agusteam.traveller.presenter.wishlist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.presenter.createCategories
import com.agusteam.traveller.presenter.createShoppingItems
import com.agusteam.traveller.presenter.wishlist.state.WishListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WishListItemViewModel : ViewModel() {
    private var _state = MutableStateFlow(WishListState())
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                favoriteItems = createShoppingItems(createCategories()),
            )
        }
    }
}