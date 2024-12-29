package com.agusteam.traveller.presenter.orders.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class OrderHistoryNavigation(val route: String) {
    data object OrderHistoryScreen : OrderHistoryNavigation("order_history")
    data object OrderDetailScreen : OrderHistoryNavigation("order_detail")
}