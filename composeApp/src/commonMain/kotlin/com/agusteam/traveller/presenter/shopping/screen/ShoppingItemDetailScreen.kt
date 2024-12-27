package com.agusteam.traveller.presenter.shopping.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.agusteam.traveller.presenter.common.ActionButton
import com.agusteam.traveller.presenter.common.CancellationPolicy
import com.agusteam.traveller.presenter.common.ItemProviderOverviewItem
import com.agusteam.traveller.presenter.common.LinkButton
import com.agusteam.traveller.presenter.common.MapDetails
import com.agusteam.traveller.presenter.getIncludedServices
import com.agusteam.traveller.presenter.shopping.composable.ShoppingItemHeader
import com.agusteam.traveller.presenter.shopping.composable.ShoppingItemIncluded
import com.agusteam.traveller.presenter.shopping.composable.ShoppingItemOverview
import com.agusteam.traveller.presenter.shopping.composable.ShoppingitemContent
import com.agusteam.traveller.presenter.shopping.model.ShoppingDetailModel
import com.agusteam.traveller.presenter.shopping.viewmodels.ShoppingitemsDetailsViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.arrival_time
import traveller.composeapp.generated.resources.cancellation_policy
import traveller.composeapp.generated.resources.cancellation_policy_details
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
import kotlin.math.ln

@Composable
fun ShoppingItemDetailScreen(
    viewModel: ShoppingitemsDetailsViewModel = koinViewModel(),
    goBack: () -> Unit,
    goProviderProfile: (id: String) -> Unit,
    payItem: () -> Unit
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.padding(
                bottom = 72.dp,
            ), // Add padding to prevent overlap
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ShoppingItemHeader(
                    images = state.value.details.galleryPhotos,
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
                ItemProviderOverviewItem(
                    modifier = Modifier, state =
                    state.value
                ) {
                    goProviderProfile(state.value.businessId)
                }
            }
            item {
                ShoppingitemContent(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    itemsDetails = listOf(
                        ShoppingDetailModel(
                            title = stringResource(Res.string.destiny),
                            description = state.value.details.destiny,
                            icon = Res.drawable.ic_pin
                        ), ShoppingDetailModel(
                            title = stringResource(Res.string.initial_payment),
                            description = state.value.initialPayment,
                            icon = Res.drawable.ic_cash
                        ), ShoppingDetailModel(
                            title = stringResource(Res.string.total_payment),
                            description = state.value.totalPayment,
                            icon = Res.drawable.ic_cash
                        ),
                        ShoppingDetailModel(
                            title = stringResource(Res.string.starting_place),
                            description = state.value.details.meetingPoint,
                            icon = Res.drawable.ic_address
                        ),
                        ShoppingDetailModel(
                            title = stringResource(Res.string.leaving_time),
                            description = state.value.details.leavingTime,
                            icon = Res.drawable.ic_clock
                        ),
                        ShoppingDetailModel(
                            title = stringResource(Res.string.arrival_time),
                            description = state.value.details.arrivingTime,
                            icon = Res.drawable.ic_clock
                        )
                    )
                )
            }
            item {
                MapDetails(lat = state.value.lat, lng = state.value.lng)
            }
            item {
                CancellationPolicy(
                    title = stringResource(Res.string.cancellation_policy),
                    description = stringResource(Res.string.cancellation_policy_details),
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {

                }
            }
            item {
                ShoppingItemIncluded(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    title = stringResource(Res.string.included_services),
                    items = getIncludedServices()
                )
            }
        }

        Row(
            Modifier.fillMaxWidth().padding(horizontal = 16.dp).align(
                Alignment.BottomCenter
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            LinkButton(
                text = state.value.totalPayment,
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
