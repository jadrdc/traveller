package com.agusteam.traveller.presenter.shopping.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.domain.models.PaymentModel
import com.agusteam.traveller.presenter.TRIP_NAME
import com.agusteam.traveller.presenter.getShoppingItemsDetails
import com.agusteam.traveller.presenter.shopping.state.TripDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShoppingitemsDetailsViewModel : ViewModel() {
    private var _state: MutableStateFlow<TripDetailState> =
        MutableStateFlow(TripDetailState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                details = getShoppingItemsDetails(),
                title = TRIP_NAME,
            )
        }

    }

    fun handleEvent(event: ShoppingDetailEvent) {
        when (event) {
            is ShoppingDetailEvent.OnPaymentTypePicked -> {
                changePaymentModel(event.paymentModel)
            }
        }
    }

    private fun changePaymentModel(paymentModel: PaymentModel) {
        updateState { currentState ->
            currentState.copy(
                selectedPaymentType = paymentModel
            )
        }
    }

    private fun updateState(updateBlock: (TripDetailState) -> TripDetailState) {
        viewModelScope.launch {
            _state.update(updateBlock)
        }
    }

    sealed interface ShoppingDetailEvent {

        class OnPaymentTypePicked(val paymentModel: PaymentModel) : ShoppingDetailEvent
    }

}
