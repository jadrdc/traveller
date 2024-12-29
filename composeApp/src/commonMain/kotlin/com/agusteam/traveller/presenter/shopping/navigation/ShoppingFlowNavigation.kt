package com.agusteam.traveller.presenter.shopping.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.agusteam.traveller.presenter.home.navigation.TripDetailScreenRoute
import com.agusteam.traveller.presenter.profile.screen.TripProviderProfileScreen
import com.agusteam.traveller.presenter.shopping.screen.ShoppingItemDetailScreen
import com.agusteam.traveller.presenter.shopping.screen.TripItemPayingScreen
import com.agusteam.traveller.presenter.shopping.viewmodels.ShoppingItemDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ShoppingFlowNavigation(
    viewModel: ShoppingItemDetailsViewModel = koinViewModel(),
    goBack: () -> Unit,
    model: TripDetailScreenRoute,
) {
    LaunchedEffect(model) {
        viewModel.handleEvent(
            ShoppingItemDetailsViewModel.ShoppingDetailEvent.ShoppingDetailLoaded(
                isFavorite = model.isFavorite,
                name = model.name,
                month = model.month,
                businessImage = model.businessImage,
                businessId = model.businessId,
                businessName = model.businessName,
                description = model.description,
                lat = model.lat.toDouble(),
                lng = model.lng.toDouble(),
                tripId = model.tripId,
                userId = model.userdId
            )
        )
    }
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
            TripProviderProfileScreen(businessId = model.businessId, onBackPressed = {
                navController.popBackStack()
            })
        }
        composable(ShoppingNavigationRoutes.ShoppingItemPayingScreen.route) {
            TripItemPayingScreen(onBackPressed = {
                navController.popBackStack()
            }, viewModel = viewModel)
        }
    }
}