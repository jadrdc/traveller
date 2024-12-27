package com.agusteam.traveller.presenter.home.navigation

import com.agusteam.traveller.domain.models.TripModel
import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationRoutes(val route: String) {
    data object ProfileScreen : NavigationRoutes("profile_screen")
    data object WishListScreen : NavigationRoutes("wishlist_screen")
    data object OrderHistoryScreen : NavigationRoutes("order_history_screen")
    data object TripHistoryItemDetailScreen : NavigationRoutes("shopping_history_detail_screen")
}

@Serializable
object HomeScreenRoute

@Serializable
data class TripDetailScreenRoute(
    val name: String,
    val lat: Float,
    val lng: Float,
    val description: String = "",
    val businessId: String = "",
    val businessImage: String = "",
    val businessName: String = "",
    val month: Int = 0,
)

