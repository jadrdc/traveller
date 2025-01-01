package com.agusteam.traveller.presenter.shopping.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.agusteam.traveller.presenter.common.ActionButton
import com.agusteam.traveller.presenter.common.AnimationLoading
import com.agusteam.traveller.presenter.common.CancellationPolicy
import com.agusteam.traveller.presenter.common.ErrorModal
import com.agusteam.traveller.presenter.common.ItemProviderOverviewItem
import com.agusteam.traveller.presenter.common.LinkButton
import com.agusteam.traveller.presenter.common.MapDetails
import com.agusteam.traveller.presenter.formatMoney
import com.agusteam.traveller.presenter.home.navigation.TripDetailScreenRoute
import com.agusteam.traveller.presenter.shopping.composable.ShoppingItemHeader
import com.agusteam.traveller.presenter.shopping.composable.ShoppingItemIncluded
import com.agusteam.traveller.presenter.shopping.composable.ShoppingItemOverview
import com.agusteam.traveller.presenter.shopping.composable.ShoppingitemContent
import com.agusteam.traveller.presenter.shopping.model.ShoppingDetailModel
import com.agusteam.traveller.presenter.shopping.viewmodels.ShoppingItemDetailsViewModel
import com.agusteam.traveller.presenter.theme.primary
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.arrival_time
import traveller.composeapp.generated.resources.cancellation_policy
import traveller.composeapp.generated.resources.destiny
import traveller.composeapp.generated.resources.ic_address
import traveller.composeapp.generated.resources.ic_cash
import traveller.composeapp.generated.resources.ic_clock
import traveller.composeapp.generated.resources.ic_pin
import traveller.composeapp.generated.resources.included_services
import traveller.composeapp.generated.resources.initial_payment
import traveller.composeapp.generated.resources.leaving_time
import traveller.composeapp.generated.resources.pay
import traveller.composeapp.generated.resources.starting_place
import traveller.composeapp.generated.resources.total_payment

@Composable
fun ShoppingItemDetailScreen(
    viewModel: ShoppingItemDetailsViewModel = koinViewModel(),
    goBack: () -> Unit,
    goProviderProfile: (id: String) -> Unit,
    payItem: () -> Unit,
    model: TripDetailScreenRoute,
) {
    LaunchedEffect(model.tripId) {
        viewModel.loadShoppingDetails(model)
    }

    val state = viewModel.state.collectAsStateWithLifecycle().value
    ErrorModal(title = state.errorModel?.title ?: "",
        message = state.errorModel?.message ?: "",
        showError = state.errorModel != null, onDismiss = {
            viewModel.handleEvent(ShoppingItemDetailsViewModel.ShoppingDetailEvent.OnErrorModalAccepted)
        })


    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.padding(
                bottom = 72.dp,
            ), // Add padding to prevent overlap
            horizontalAlignment = Alignment.CenterHorizontally // Centers content horizontally
            ,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (state.tripId.isNotBlank()) {
                item {
                    ShoppingItemHeader(
                        images = state.galleryPhotos,
                        isSavedForLater = state.isMarkedAsFavorite,
                        onBackPressed = goBack
                    ) {
                        viewModel.handleEvent(ShoppingItemDetailsViewModel.ShoppingDetailEvent.MarkFavorite)
                    }
                }
                item {
                    ShoppingItemOverview(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        title = state.title,
                        description = state.description
                    )
                }
            }
            if (state.isLoadingContent) {
                item {
                    Box(Modifier.align(CenterEnd).padding(top = 16.dp)) {
                        AnimationLoading()
                    }
                }
            } else if (state.tripId.isNotBlank()) {

                item {
                    ItemProviderOverviewItem(
                        modifier = Modifier, state =
                        state
                    ) {
                        goProviderProfile(state.businessId)
                    }
                }
                item {
                    ShoppingitemContent(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        itemsDetails = listOf(
                            ShoppingDetailModel(
                                title = stringResource(Res.string.destiny),
                                description = state.destiny,
                                icon = Res.drawable.ic_pin
                            ), ShoppingDetailModel(
                                title = stringResource(Res.string.initial_payment),
                                description = formatMoney(state.initialPayment),
                                icon = Res.drawable.ic_cash
                            ), ShoppingDetailModel(
                                title = stringResource(Res.string.total_payment),
                                description = formatMoney(state.totalPayment),
                                icon = Res.drawable.ic_cash
                            ),
                            ShoppingDetailModel(
                                title = stringResource(Res.string.starting_place),
                                description = state.meetingPoint,
                                icon = Res.drawable.ic_address
                            ),
                            ShoppingDetailModel(
                                title = stringResource(Res.string.leaving_time),
                                description = state.leavingTime,
                                icon = Res.drawable.ic_clock
                            ),
                            ShoppingDetailModel(
                                title = stringResource(Res.string.arrival_time),
                                description = state.arrivingTime,
                                icon = Res.drawable.ic_clock
                            )
                        )
                    )
                }
                item {
                    MapDetails(lat = state.lat, lng = state.lng)
                }
                item {
                    CancellationPolicy(
                        title = stringResource(Res.string.cancellation_policy),
                        description = state.cancellationPolicy,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {

                    }
                }
                item {
                    ShoppingItemIncluded(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        title = stringResource(Res.string.included_services),
                        items = state.includedServices
                    )
                }

            }
        }


        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(enabled = false) {}, // Prevents interaction
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = primary)
            }
        }

        if (state.tripId.isNotBlank())
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 16.dp).align(
                    Alignment.BottomCenter
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                LinkButton(
                    text = formatMoney(state.totalPayment),
                ) {

                }

                ActionButton(
                    text = stringResource(Res.string.pay),
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    payItem()
                }
            }
    }
}
