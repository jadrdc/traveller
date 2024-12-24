package com.agusteam.traveller.presenter.profile.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.agusteam.traveller.domain.models.TripProviderModel
import com.agusteam.traveller.presenter.common.BackButton
import com.agusteam.traveller.presenter.common.ProviderOverViewItem
import com.agusteam.traveller.presenter.common.ProviderProfileCategory
import com.agusteam.traveller.presenter.common.ProviderProfileOverview
import com.agusteam.traveller.presenter.getProvider
import com.agusteam.traveller.presenter.orders.composable.UpcomingTripItemSection

@Composable
fun TripProviderProfileScreen(tripProviderModel: TripProviderModel = getProvider(), onBackPressed: () -> Unit) {

    Column(Modifier.background(Color.White).padding(horizontal = 16.dp)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                BackButton { onBackPressed() }
            }
            item {
                ProviderOverViewItem(
                    tripProviderModel = tripProviderModel
                )
            }
            item {
                ProviderProfileOverview(tripProviderModel)
            }
            item {
                ProviderProfileCategory(
                    modifier = Modifier.padding(top = 16.dp),
                    tripProviderModel.categoryModel
                )
            }
            item {
                UpcomingTripItemSection(
                    upcomingTripItemList = tripProviderModel.upcomingTrips,
                ) {}
            }
        }
    }
}