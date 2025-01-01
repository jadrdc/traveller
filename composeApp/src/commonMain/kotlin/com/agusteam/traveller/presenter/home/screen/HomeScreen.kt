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
import com.agusteam.traveller.presenter.home.navigation.TripDetailScreenRoute
import com.agusteam.traveller.presenter.home.state.HomeOption
import com.agusteam.traveller.presenter.home.viewmodel.HomeViewModel
import com.agusteam.traveller.presenter.orders.navigation.OrderHistoryNavigationFlow
import com.agusteam.traveller.presenter.profile.screen.ProfileScreen
import com.agusteam.traveller.presenter.theme.CustomTypography
import com.agusteam.traveller.presenter.wishlist.navigation.WishListNavigationFlow
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
@Preview
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onNavigateDetails: (TripDetailScreenRoute) -> Unit,
    logout: () -> Unit
) {
    val navController = rememberNavController()
    val homeState = viewModel.state.collectAsStateWithLifecycle().value


    MaterialTheme(typography = CustomTypography()) {
        Scaffold(topBar = {}, bottomBar = {
            if (homeState.currentNavigationOption != HomeOption.SHOPPING_ITEM_DETAIL) BottomNavigationBar(
                navController = navController, visible = true
            )
        }, modifier = Modifier.fillMaxSize(), containerColor = Color.White
        ) { innnerPadding ->
            Box(modifier = Modifier.padding(innnerPadding).background(Color.White)) {
                NavHost(
                    navController = navController,
                    startDestination = NavigationRoutes.HomeScreen.route

                ) {
                    composable(
                        NavigationRoutes.HomeScreen.route
                    ) {
                        viewModel.handleEvent(HomeViewModel.HomeEvent.ChangeHomeTab(HomeOption.EXPLORE))
                        ExploreScreen { tripModel, userId ->
                            onNavigateDetails(
                                TripDetailScreenRoute(
                                    destiny = tripModel.destiny,
                                    cancellationPolicy = tripModel.cancellationPolicy,
                                    images = tripModel.images,
                                    userdId = userId,
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
                            )
                        }
                    }
                    composable(NavigationRoutes.ProfileScreen.route) {
                        viewModel.handleEvent(HomeViewModel.HomeEvent.ChangeHomeTab(HomeOption.PROFILE))
                        ProfileScreen(logout = logout)
                    }
                    composable(NavigationRoutes.WishListScreen.route) {
                        viewModel.handleEvent(HomeViewModel.HomeEvent.ChangeHomeTab(HomeOption.WISHLIST))
                        WishListNavigationFlow()
                    }

                    composable(NavigationRoutes.OrderHistoryScreen.route) {
                        OrderHistoryNavigationFlow(onClick = {
                            viewModel.handleEvent(HomeViewModel.HomeEvent.ChangeHomeTab(HomeOption.SHOPPING_ITEM_DETAIL))
                        }, onBackPreesed = {
                            viewModel.handleEvent(HomeViewModel.HomeEvent.ChangeHomeTab(HomeOption.TRIP))
                        })
                        LaunchedEffect(Unit) {
                            viewModel.handleEvent(HomeViewModel.HomeEvent.ChangeHomeTab(HomeOption.TRIP))
                        }
                    }
                }
            }
        }
    }
}
