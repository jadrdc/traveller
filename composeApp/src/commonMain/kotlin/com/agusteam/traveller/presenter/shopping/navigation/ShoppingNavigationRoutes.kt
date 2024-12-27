package com.agusteam.traveller.presenter.shopping.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ShoppingNavigationRoutes(val route: String) {
  /*  data object ProviderProfileScreen : ShoppingNavigationRoutes("profile_provider/{id}") {
        fun createRoute(id: String): String = "profile_provider/$id"
    }
*/
    data object ShoppingItemDetailScreen : ShoppingNavigationRoutes("shopping_item_detail")
    data object ShoppingItemPayingScreen : ShoppingNavigationRoutes("shopping_item_paying")

}

@Serializable
data class ProviderProfileScreen(
    val businessId: String
)

