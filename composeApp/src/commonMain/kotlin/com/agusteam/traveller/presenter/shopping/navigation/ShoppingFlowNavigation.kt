package com.agusteam.traveller.presenter.shopping.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.agusteam.traveller.presenter.profile.screen.TripProviderProfileScreen
import com.agusteam.traveller.presenter.shopping.screen.ShoppingItemDetailScreen
import com.agusteam.traveller.presenter.shopping.screen.TripItemPayingScreen
import com.agusteam.traveller.presenter.shopping.viewmodels.ShoppingitemsDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ShoppingFlowNavigation(
    viewModel: ShoppingitemsDetailsViewModel = koinViewModel(),
    goBack: () -> Unit
) {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = ShoppingNavigationRoutes.ShoppingItemDetailScreen.route
    ) {
       composable(ShoppingNavigationRoutes.ShoppingItemDetailScreen.route) {
            ShoppingItemDetailScreen(viewModel, goBack = { goBack() }, payItem = {
                navController.navigate(ShoppingNavigationRoutes.ShoppingItemPayingScreen.route)
            }, goProviderProfile = {
                navController.navigate(ShoppingNavigationRoutes.ProviderProfileScreen.route)
            })
        }
        composable(ShoppingNavigationRoutes.ProviderProfileScreen.route) {
            TripProviderProfileScreen {
                navController.popBackStack()
            }
        }
        composable(ShoppingNavigationRoutes.ShoppingItemPayingScreen.route) {
            TripItemPayingScreen(onBackPressed = {
                navController.popBackStack()
            }, viewModel = viewModel)
        }
    }
}