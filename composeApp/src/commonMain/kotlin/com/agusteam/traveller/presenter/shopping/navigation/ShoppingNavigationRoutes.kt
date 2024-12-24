package com.agusteam.traveller.presenter.shopping.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ShoppingNavigationRoutes(val route: String) {
    data object ProviderProfileScreen : ShoppingNavigationRoutes("profile_provider")
    data object ShoppingItemDetailScreen : ShoppingNavigationRoutes("shopping_item_detail")
    data object ShoppingItemPayingScreen : ShoppingNavigationRoutes("shopping_item_paying")

}