package com.agusteam.traveller.presenter.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.agusteam.traveller.presenter.common.BottomNavigationBar
import com.agusteam.traveller.presenter.explore.screen.ExploreScreen
import com.agusteam.traveller.presenter.home.navigation.NavigationRoutes
import com.agusteam.traveller.presenter.home.state.HomeOption
import com.agusteam.traveller.presenter.home.viewmodel.HomeViewModel
import com.agusteam.traveller.presenter.orders.navigation.OrderHistoryNavigationFlow
import com.agusteam.traveller.presenter.profile.screen.ProfileScreen
import com.agusteam.traveller.presenter.shopping.navigation.ShoppingFlowNavigation
import com.agusteam.traveller.presenter.theme.CustomTypography
import com.agusteam.traveller.presenter.wishlist.navigation.WishListNavigationFlow
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
@Preview
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val navController = rememberNavController()
    val homeState = viewModel.state.collectAsStateWithLifecycle().value
    LaunchedEffect(Unit) {

    }


    MaterialTheme(typography = CustomTypography()) {
        Scaffold(topBar = {}, bottomBar = {
            if (homeState.currentNavigationOption.value != HomeOption.SHOPPING_ITEM_DETAIL) BottomNavigationBar(
                navController = navController,
                visible = true
            )
        }, modifier = Modifier.fillMaxSize(), containerColor = Color.White
        ) { innnerPadding ->
            Box(modifier = Modifier.padding(innnerPadding).background(Color.White)) {
                NavHost(
                    navController = navController,
                    startDestination = NavigationRoutes.HomeScreen.route
                ) {
                    composable(NavigationRoutes.HomeScreen.route) {
                        homeState.changeHomeOption(HomeOption.EXPLORE)
                        ExploreScreen { navController.navigate(NavigationRoutes.TripDetailScreen.route) }
                    }
                    composable(NavigationRoutes.ProfileScreen.route) {
                        homeState.changeHomeOption(HomeOption.PROFILE)
                        ProfileScreen()
                    }
                    composable(NavigationRoutes.WishListScreen.route) {
                        homeState.changeHomeOption(HomeOption.WISHLIST)
                        WishListNavigationFlow()
                    }
                    composable(NavigationRoutes.TripDetailScreen.route) {
                        homeState.changeHomeOption(HomeOption.SHOPPING_ITEM_DETAIL)
                        ShoppingFlowNavigation(goBack = { navController.popBackStack() })
                    }

                    composable(NavigationRoutes.OrderHistoryScreen.route) {
                        OrderHistoryNavigationFlow(onClick = {
                            homeState.changeHomeOption(HomeOption.SHOPPING_ITEM_DETAIL)
                        }, onBackPreesed = {
                            homeState.changeHomeOption(HomeOption.TRIP)
                        })
                        LaunchedEffect(Unit) {
                            homeState.changeHomeOption(HomeOption.TRIP)
                        }
                    }
                }
            }
        }
    }
}
