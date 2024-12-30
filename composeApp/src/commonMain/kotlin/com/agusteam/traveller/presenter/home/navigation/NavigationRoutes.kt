package com.agusteam.traveller.presenter.home.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationRoutes(val route: String) {
    data object HomeScreen : NavigationRoutes("home_screen")
    data object ProfileScreen : NavigationRoutes("profile_screen")
    data object TripDetailScreen : NavigationRoutes("shopping_detail_screen")
    data object WishListScreen : NavigationRoutes("wishlist_screen")
    data object OrderHistoryScreen : NavigationRoutes("order_history_screen")
}

@Serializable
object HomeScreenRoute

@Serializable
data class TripDetailScreenRoute(
    val tripId: String,
    val name: String,
    val lat: Float,
    val lng: Float,
    val description: String = "",
    val businessId: String = "",
    val businessImage: String = "",
    val businessName: String = "",
    val month: Int = 0,
    val isFavorite: Boolean,
    val userdId: String,
    val images: List<String>, val cancellationPolicy: String
)

