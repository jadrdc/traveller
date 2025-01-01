package com.agusteam.traveller.presenter.orders.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.agusteam.traveller.presenter.orders.screen.OrderHistoryScreen
import com.agusteam.traveller.presenter.orders.screen.OrderItemDetailsScreen

@Composable
fun OrderHistoryNavigationFlow(onClick: () -> Unit, onBackPreesed: () -> Unit) {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = OrderHistoryNavigation.OrderHistoryScreen.route
    ) {
        composable(OrderHistoryNavigation.OrderHistoryScreen.route) {
            OrderHistoryScreen {
                onClick()
                navController.navigate(OrderHistoryNavigation.OrderDetailScreen.route)
            }
        }
        composable(OrderHistoryNavigation.OrderDetailScreen.route) {
            /*   OrderItemDetailsScreen(goBack = {
                   onBackPreesed()
                   navController.popBackStack()
               }, model = model)*/
        }
    }
}