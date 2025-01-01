package com.agusteam.traveller.presenter.orders.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.presenter.getIncludedServices
import com.agusteam.traveller.presenter.shopping.state.TripDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrderDetailViewModel : ViewModel() {
    private var _state = MutableStateFlow(TripDetailState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                title = "Trudille",

                )
        }

    }
}