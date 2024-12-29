package com.agusteam.traveller.presenter.signup.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class SignupNavigationRoutes(val route: String) {
    data object LoginScreen : SignupNavigationRoutes("login_screen")
    data object SignUpCreateScreen : SignupNavigationRoutes("sign_up_screen")
    @Serializable
    data object HomeScreen : SignupNavigationRoutes("home_screen")
}