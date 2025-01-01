package com.agusteam.traveller.presenter.wishlist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
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
            WishListScreen { tripModel ->
                val route = WishListItemDetailScreenRoute(
                    destiny = tripModel.destiny,
                    cancellationPolicy = tripModel.cancellation_policy,
                    images = tripModel.images,
                    tripId = tripModel.id,
                    isFavorite = tripModel.isSavedForLater,
                    month = tripModel.businessModel?.month ?: 0,
                    businessImage = tripModel.businessModel?.image ?: "",
                    businessName = tripModel.businessModel?.name ?: "",
                    businessId = tripModel.businessId,
                    name = tripModel.name,
                    description = tripModel.description,
                    lat = tripModel.lat.toFloat(),
                    lng = tripModel.lng.toFloat(),
                    initialPayment = tripModel.initialPayment,
                    meetingPoint = tripModel.meetingPoint,
                    arrivingTime = tripModel.arrivingTime,
                    leavingTime = tripModel.leavingTime,
                    price = tripModel.price,
                )
                navController.navigate(route)
            }
        }
        composable<WishListItemDetailScreenRoute> { backStackEntry ->
            val model = backStackEntry.toRoute<WishListItemDetailScreenRoute>()
            OrderItemDetailsScreen(
                model = model,
                goBack = {
                    navController.popBackStack()
                })
        }
    }

}