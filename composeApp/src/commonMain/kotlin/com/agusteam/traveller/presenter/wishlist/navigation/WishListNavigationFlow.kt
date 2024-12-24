package com.agusteam.traveller.presenter.wishlist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.agusteam.traveller.presenter.orders.screen.OrderItemDetailsScreen
import com.agusteam.traveller.presenter.wishlist.screen.WishListScreen

@Composable
fun WishListNavigationFlow() {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = WishListNavigation.WishListScreen.route
    ) {
        composable(WishListNavigation.WishListScreen.route) {
            WishListScreen { navController.navigate(WishListNavigation.WishListItemDetailScreen.route) }
        }
        composable(WishListNavigation.WishListItemDetailScreen.route) {
            OrderItemDetailsScreen(goBack = {
                navController.popBackStack()
            })
        }
    }

}