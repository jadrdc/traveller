package com.agusteam.traveller.presenter.wishlist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.agusteam.traveller.presenter.home.navigation.TripDetailScreenRoute
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
                    cancellationPolicy = tripModel.cancellationPolicy,
                    images = tripModel.images,
                    tripId = tripModel.id,
                    isFavorite = tripModel.isSavedForLater,
                    month = tripModel.month,
                    businessImage = tripModel.businessImage,
                    businessName = tripModel.businessName,
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
        composable<WishListItemDetailScreenRoute> {
            OrderItemDetailsScreen(goBack = {
                navController.popBackStack()
            })
        }
    }

}