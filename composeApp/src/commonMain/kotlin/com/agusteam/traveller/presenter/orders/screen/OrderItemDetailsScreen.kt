package com.agusteam.traveller.presenter.orders.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.agusteam.traveller.presenter.common.CancellationPolicy
import com.agusteam.traveller.presenter.common.ItemProviderOverviewItem
import com.agusteam.traveller.presenter.common.MapDetails
import com.agusteam.traveller.presenter.orders.viewmodels.WishListOrderDetailViewModel
import com.agusteam.traveller.presenter.shopping.composable.ShoppingItemHeader
import com.agusteam.traveller.presenter.shopping.composable.ShoppingItemIncluded
import com.agusteam.traveller.presenter.shopping.composable.ShoppingItemOverview
import com.agusteam.traveller.presenter.shopping.composable.ShoppingitemContent
import com.agusteam.traveller.presenter.shopping.model.ShoppingDetailModel
import com.agusteam.traveller.presenter.wishlist.navigation.WishListItemDetailScreenRoute
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.cancellation_policy
import traveller.composeapp.generated.resources.destiny
import traveller.composeapp.generated.resources.ic_pin
import traveller.composeapp.generated.resources.trip_categories

@Composable
fun OrderItemDetailsScreen(
    viewModel: WishListOrderDetailViewModel = koinViewModel(),
    goBack: () -> Unit,
    goDetails: (String) -> Unit,
    model: WishListItemDetailScreenRoute
) {
    LaunchedEffect(model.tripId, model.businessId) {
        viewModel.handleEvent(
            WishListOrderDetailViewModel.OrderDetailsEvent.OrderDetailsLoadIncludeServices(
                model.tripId, model.businessName, model.businessImage, model.month
            )
        )
    }

    val state = viewModel.state.collectAsStateWithLifecycle()
    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier, // Add padding to prevent overlap
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ShoppingItemHeader(
                    images = model.images,
                    isSavedForLater = true,
                    onBackPressed = goBack
                )
            }
            item {
                ShoppingItemOverview(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    title = model.name,
                    description = model.description
                )
            }
            item {
                ItemProviderOverviewItem(
                    modifier = Modifier, state =
                    state.value.itemProviderState
                ) {
                    goDetails(model.businessId)
                }
            }
            item {
                ShoppingitemContent(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    itemsDetails = listOf(
                        ShoppingDetailModel(
                            title = stringResource(Res.string.destiny),
                            description = model.destiny,
                            icon = Res.drawable.ic_pin
                        ),
                    )
                )
            }
            item {
                MapDetails(lat = model.lat.toDouble(), lng = model.lng.toDouble())
            }
            item {
                CancellationPolicy(
                    title = stringResource(Res.string.cancellation_policy),
                    description = model.cancellationPolicy,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {

                }
            }
            item {
                ShoppingItemIncluded(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    title = stringResource(Res.string.trip_categories),
                    items = state.value.includedServices
                )
            }
        }

    }
}