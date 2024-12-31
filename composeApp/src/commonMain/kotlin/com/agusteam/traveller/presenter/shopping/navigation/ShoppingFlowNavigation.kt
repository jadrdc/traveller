package com.agusteam.traveller.presenter.shopping.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.agusteam.traveller.presenter.home.navigation.TripDetailScreenRoute
import com.agusteam.traveller.presenter.profile.screen.TripProviderProfileScreen
import com.agusteam.traveller.presenter.shopping.screen.ShoppingItemDetailScreen
import com.agusteam.traveller.presenter.shopping.screen.TripItemPayingScreen


@Composable
fun ShoppingFlowNavigation(
    goBack: () -> Unit,
    model: TripDetailScreenRoute,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ShoppingNavigationRoutes.ShoppingItemDetailScreen.route
    ) {
        composable(ShoppingNavigationRoutes.ShoppingItemDetailScreen.route) {
            ShoppingItemDetailScreen(goBack = { goBack() }, payItem = {
                navController.navigate(ShoppingNavigationRoutes.ShoppingItemPayingScreen.route)
            }, goProviderProfile = { id ->
                navController.navigate(ProviderProfileScreen(id))
            }, model = model)
        }
        composable<ProviderProfileScreen> { backStackEntry ->
            val tripProviderModel = backStackEntry.toRoute<ProviderProfileScreen>()
            TripProviderProfileScreen(businessId = tripProviderModel.businessId, onBackPressed = {
                navController.navigate(ShoppingNavigationRoutes.ShoppingItemDetailScreen.route)
            })
        }
        composable(ShoppingNavigationRoutes.ShoppingItemPayingScreen.route) {
            TripItemPayingScreen(onBackPressed = {
                navController.popBackStack()
            })
        }
    }
}