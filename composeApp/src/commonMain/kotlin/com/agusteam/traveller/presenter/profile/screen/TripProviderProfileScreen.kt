package com.agusteam.traveller.presenter.profile.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.agusteam.traveller.presenter.common.NavigationBar
import com.agusteam.traveller.presenter.common.ProviderOverViewItem
import com.agusteam.traveller.presenter.common.ProviderProfileCategory
import com.agusteam.traveller.presenter.common.ProviderProfileOverview
import com.agusteam.traveller.presenter.orders.composable.UpcomingTripItemSection
import com.agusteam.traveller.presenter.profile.composable.TripProviderOverviewShimmer
import com.agusteam.traveller.presenter.profile.viewmodels.TripProviderViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.agency_title

@Composable
fun TripProviderProfileScreen(
    tripProviderModel: TripProviderViewModel = koinViewModel(),
    onBackPressed: () -> Unit,
    businessId: String
) {
    LaunchedEffect(Unit) {
        tripProviderModel.handlerEvent(
            TripProviderViewModel.TripProviderEvent.TripProviderDetailsLoading(
                businessId
            )
        )
    }
    val state = tripProviderModel.state.collectAsStateWithLifecycle().value
    Column(Modifier.background(Color.White).padding(horizontal = 16.dp)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                NavigationBar(title = stringResource(Res.string.agency_title)) { onBackPressed() }
            }
            if (state.tripProviderModel != null) {
                item {
                    ProviderOverViewItem(
                        tripProviderModel = state.tripProviderModel
                    )
                }

                item {
                    ProviderProfileOverview(state.tripProviderModel)
                }

                item {
                    ProviderProfileCategory(
                        modifier = Modifier,
                        state.tripProviderModel.categoryModel
                    )
                }
            } else {
                item {
                    TripProviderOverviewShimmer()
                }
            }
            if (state.tripProviderModel != null) item {
                UpcomingTripItemSection(
                    upcomingTripItemList = state.upcomingTrips,
                ) {}
            }


        }
    }
}