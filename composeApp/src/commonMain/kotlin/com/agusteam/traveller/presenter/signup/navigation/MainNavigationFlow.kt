package com.agusteam.traveller.presenter.signup.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.agusteam.traveller.presenter.home.navigation.TripDetailScreenRoute
import com.agusteam.traveller.presenter.home.screen.HomeScreen
import com.agusteam.traveller.presenter.shopping.navigation.ShoppingFlowNavigation
import com.agusteam.traveller.presenter.signup.screen.LoginScreen
import com.agusteam.traveller.presenter.signup.screen.SignUpAccountScreen


@Composable
fun MainNavigationFlow() {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = SignupNavigationRoutes.LoginScreen.route
    ) {
        composable(SignupNavigationRoutes.LoginScreen.route) {
            LoginScreen(onLogin = {
                navController.navigate(SignupNavigationRoutes.HomeScreen) {
                }
            }, onSignUp = {
                navController.navigate(SignupNavigationRoutes.SignUpCreateScreen.route)
            })
        }
        composable(SignupNavigationRoutes.SignUpCreateScreen.route) {
            SignUpAccountScreen(onBackPressed = { navController.popBackStack() })
        }
        composable<SignupNavigationRoutes.HomeScreen> {
            HomeScreen(onNavigateDetails = { route ->
                navController.navigate(route)
            }, logout = {
                navController.navigate(SignupNavigationRoutes.LoginScreen.route) {
                }
            })
        }
        composable<TripDetailScreenRoute> { backStackEntry ->
            val model = backStackEntry.toRoute<TripDetailScreenRoute>()
            ShoppingFlowNavigation(
                model = model,
                goBack = { navController.navigate(SignupNavigationRoutes.HomeScreen) })
        }
    }
}