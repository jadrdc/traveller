package com.agusteam.traveller.presenter.orders.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.agusteam.traveller.presenter.formatMoney
import com.agusteam.traveller.presenter.getIncludedServices
import com.agusteam.traveller.presenter.orders.viewmodels.OrderDetailViewModel
import com.agusteam.traveller.presenter.shopping.composable.ShoppingItemHeader
import com.agusteam.traveller.presenter.shopping.composable.ShoppingItemIncluded
import com.agusteam.traveller.presenter.shopping.composable.ShoppingItemOverview
import com.agusteam.traveller.presenter.shopping.composable.ShoppingitemContent
import com.agusteam.traveller.presenter.shopping.model.ShoppingDetailModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.arrival_time
import traveller.composeapp.generated.resources.destiny
import traveller.composeapp.generated.resources.ic_address
import traveller.composeapp.generated.resources.ic_cash
import traveller.composeapp.generated.resources.ic_clock
import traveller.composeapp.generated.resources.ic_pin
import traveller.composeapp.generated.resources.initial_payment
import traveller.composeapp.generated.resources.leaving_time
import traveller.composeapp.generated.resources.starting_place
import traveller.composeapp.generated.resources.total_payment
import traveller.composeapp.generated.resources.trip_categories

@Composable
fun OrderItemDetailsScreen(viewModel: OrderDetailViewModel = koinViewModel(), goBack: () -> Unit) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier, // Add padding to prevent overlap
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ShoppingItemHeader(
                    images = state.value.galleryPhotos,
                    isSavedForLater = state.value.isMarkedAsFavorite,
                    onBackPressed = goBack
                )
            }
            item {
                ShoppingItemOverview(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    title = state.value.title,
                    description = state.value.description
                )
            }
            item {
                ShoppingitemContent(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    itemsDetails = listOf(
                        ShoppingDetailModel(
                            title = stringResource(Res.string.destiny),
                            description = state.value.destiny,
                            icon = Res.drawable.ic_pin
                        ), ShoppingDetailModel(
                            title = stringResource(Res.string.initial_payment),
                            description = formatMoney(state.value.initialPayment),
                            icon = Res.drawable.ic_cash
                        ), ShoppingDetailModel(
                            title = stringResource(Res.string.total_payment),
                            description = formatMoney(state.value.totalPayment)
                                ,
                            icon = Res.drawable.ic_cash
                        ),
                        ShoppingDetailModel(
                            title = stringResource(Res.string.starting_place),
                            description = state.value.meetingPoint,
                            icon = Res.drawable.ic_address
                        ),
                        ShoppingDetailModel(
                            title = stringResource(Res.string.leaving_time),
                            description = state.value.leavingTime,
                            icon = Res.drawable.ic_clock
                        ),
                        ShoppingDetailModel(
                            title = stringResource(Res.string.arrival_time),
                            description = state.value.arrivingTime,
                            icon = Res.drawable.ic_clock
                        )
                    )
                )
            }
            item {
                ShoppingItemIncluded(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    title = stringResource(Res.string.trip_categories),
                    items = getIncludedServices()
                )
            }
        }

    }
}