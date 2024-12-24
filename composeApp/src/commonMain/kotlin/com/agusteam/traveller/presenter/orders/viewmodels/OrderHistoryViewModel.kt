package com.agusteam.traveller.presenter.orders.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.presenter.createCategories
import com.agusteam.traveller.presenter.createShoppingItems
import com.agusteam.traveller.presenter.orders.state.OrderHistoryState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrderHistoryViewModel : ViewModel() {
    private var _state = MutableStateFlow(OrderHistoryState())
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                oldItems = createShoppingItems(createCategories()),
                upcomingItems = createShoppingItems(createCategories())
            )
        }
    }
}