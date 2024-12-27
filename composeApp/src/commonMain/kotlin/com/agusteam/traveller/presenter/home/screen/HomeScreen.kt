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
import androidx.navigation.toRoute
import com.agusteam.traveller.presenter.common.BottomNavigationBar
import com.agusteam.traveller.presenter.explore.screen.ExploreScreen
import com.agusteam.traveller.presenter.home.navigation.HomeScreenRoute
import com.agusteam.traveller.presenter.home.navigation.NavigationRoutes
import com.agusteam.traveller.presenter.home.navigation.TripDetailScreenRoute
import com.agusteam.traveller.presenter.home.state.HomeOption
import com.agusteam.traveller.presenter.home.viewmodel.HomeViewModel
import com.agusteam.traveller.presenter.orders.navigation.OrderHistoryNavigationFlow
import com.agusteam.traveller.presenter.profile.screen.ProfileScreen
import com.agusteam.traveller.presenter.shopping.navigation.ShoppingFlowNavigation
import com.agusteam.traveller.presenter.shopping.viewmodels.ShoppingitemsDetailsViewModel
import com.agusteam.traveller.presenter.theme.CustomTypography
import com.agusteam.traveller.presenter.wishlist.navigation.WishListNavigationFlow
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
@Preview
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val navController = rememberNavController()
    val homeState = viewModel.state.collectAsStateWithLifecycle().value
    val shoppingViewModel: ShoppingitemsDetailsViewModel = koinViewModel()


    MaterialTheme(typography = CustomTypography()) {
        Scaffold(topBar = {}, bottomBar = {
            if (homeState.currentNavigationOption != HomeOption.SHOPPING_ITEM_DETAIL) BottomNavigationBar(
                navController = navController, visible = true
            )
        }, modifier = Modifier.fillMaxSize(), containerColor = Color.White
        ) { innnerPadding ->
            Box(modifier = Modifier.padding(innnerPadding).background(Color.White)) {
                NavHost(
                    navController = navController, startDestination = HomeScreenRoute
                ) {
                    composable<HomeScreenRoute> {
                        viewModel.handleEvent(HomeViewModel.HomeEvent.ChangeHomeTab(HomeOption.EXPLORE))
                        ExploreScreen { tripModel ->
                            navController.navigate(
                                TripDetailScreenRoute(
                                    month = tripModel.month,
                                    businessImage = tripModel.businessImage,
                                    businessName = tripModel.businessName,
                                    businessId = tripModel.businessId,
                                    name = tripModel.name,
                                    description = tripModel.description,
                                    lat = tripModel.lat.toFloat(),
                                    lng = tripModel.lng.toFloat()
                                )
                            )
                        }
                    }
                    composable(NavigationRoutes.ProfileScreen.route) {
                        viewModel.handleEvent(HomeViewModel.HomeEvent.ChangeHomeTab(HomeOption.PROFILE))
                        ProfileScreen()
                    }
                    composable(NavigationRoutes.WishListScreen.route) {
                        viewModel.handleEvent(HomeViewModel.HomeEvent.ChangeHomeTab(HomeOption.WISHLIST))
                        WishListNavigationFlow()
                    }
                    composable<TripDetailScreenRoute> { backStackEntry ->
                        val model = backStackEntry.toRoute<TripDetailScreenRoute>()
                        viewModel.handleEvent(HomeViewModel.HomeEvent.ChangeHomeTab(HomeOption.SHOPPING_ITEM_DETAIL))
                        LaunchedEffect(Unit) {
                            shoppingViewModel.handleEvent(
                                ShoppingitemsDetailsViewModel.ShoppingDetailEvent.ShoppingDetailLoaded(
                                    name = model.name,
                                    month = model.month,
                                    businessImage = model.businessImage,
                                    businessId = model.businessId,
                                    businessName = model.businessName,
                                    description = model.description,
                                    lat = model.lat.toDouble(),
                                    lng = model.lng.toDouble()
                                )
                            )
                        }
                        ShoppingFlowNavigation(
                            viewModel = shoppingViewModel,
                            goBack = { navController.popBackStack() })
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
