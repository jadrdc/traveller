package com.agusteam.traveller.presenter.shopping.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.agusteam.traveller.presenter.home.navigation.TripDetailScreenRoute
import com.agusteam.traveller.presenter.profile.screen.TripProviderProfileScreen
import com.agusteam.traveller.presenter.profile.viewmodels.TripProviderViewModel
import com.agusteam.traveller.presenter.shopping.screen.ShoppingItemDetailScreen
import com.agusteam.traveller.presenter.shopping.screen.TripItemPayingScreen
import com.agusteam.traveller.presenter.shopping.viewmodels.ShoppingitemsDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ShoppingFlowNavigation(
    viewModel: ShoppingitemsDetailsViewModel = koinViewModel(),
    goBack: () -> Unit,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ShoppingNavigationRoutes.ShoppingItemDetailScreen.route
    ) {
        composable(ShoppingNavigationRoutes.ShoppingItemDetailScreen.route) {
            ShoppingItemDetailScreen(viewModel, goBack = { goBack() }, payItem = {
                navController.navigate(ShoppingNavigationRoutes.ShoppingItemPayingScreen.route)
            }, goProviderProfile = { id ->
                navController.navigate(ProviderProfileScreen(id))
            })
        }
        composable<ProviderProfileScreen> { backStackEntry ->
            val model = backStackEntry.toRoute<ProviderProfileScreen>()
            TripProviderProfileScreen(id = model.businessId) {
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