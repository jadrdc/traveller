package com.agusteam.traveller.presenter.orders.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.agusteam.traveller.presenter.orders.composable.PreviousTripItemSection
import com.agusteam.traveller.presenter.orders.composable.UpcomingTripItemSection
import com.agusteam.traveller.presenter.orders.viewmodels.OrderHistoryViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OrderHistoryScreen(
    viewModel: OrderHistoryViewModel = koinViewModel(),
    goDetails: () -> Unit
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LazyColumn(Modifier.padding(horizontal = 16.dp, vertical = 32.dp)) {
        item {
            UpcomingTripItemSection(upcomingTripItemList = state.upcomingItems) {
                goDetails()
            }
        }
        item {
            PreviousTripItemSection(oldItems = state.oldItems) {
                goDetails()
            }
        }
    }
}