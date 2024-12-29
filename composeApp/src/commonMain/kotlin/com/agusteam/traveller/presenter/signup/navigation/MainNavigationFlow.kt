package com.agusteam.traveller.presenter.signup.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.agusteam.traveller.presenter.home.screen.HomeScreen
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
                    popUpTo(SignupNavigationRoutes.LoginScreen.route) { inclusive = true }
                }
            }, onSignUp = {
                navController.navigate(SignupNavigationRoutes.SignUpCreateScreen.route)
            })
        }
        composable(SignupNavigationRoutes.SignUpCreateScreen.route) {
            SignUpAccountScreen(onBackPressed = { navController.popBackStack() })
        }
        composable<SignupNavigationRoutes.HomeScreen> {
            HomeScreen()
        }
    }


}