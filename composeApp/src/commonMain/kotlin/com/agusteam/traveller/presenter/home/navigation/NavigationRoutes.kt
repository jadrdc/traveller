package com.agusteam.traveller.presenter.home.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationRoutes(val route: String) {
    data object HomeScreen : NavigationRoutes("home_screen")
    data object ProfileScreen : NavigationRoutes("profile_screen")
    data object WishListScreen : NavigationRoutes("wishlist_screen")
    data object OrderHistoryScreen : NavigationRoutes("order_history_screen")
    data object TripDetailScreen : NavigationRoutes("shopping_detail_screen")
    data object TripHistoryItemDetailScreen : NavigationRoutes("shopping_history_detail_screen")
}
