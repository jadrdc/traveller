package com.agusteam.traveller.presenter.shopping.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.agusteam.traveller.presenter.common.ActionButton
import com.agusteam.traveller.presenter.formatMoney
import com.agusteam.traveller.presenter.shopping.composable.ShoppingPaymentWay
import com.agusteam.traveller.presenter.shopping.composable.TripItemPayDetail
import com.agusteam.traveller.presenter.shopping.composable.TripItemPayHeader
import com.agusteam.traveller.presenter.shopping.model.ShoppingDetailModel
import com.agusteam.traveller.presenter.shopping.viewmodels.ShoppingItemDetailsViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.ic_address
import traveller.composeapp.generated.resources.ic_cash
import traveller.composeapp.generated.resources.initial_payment
import traveller.composeapp.generated.resources.leaving_date
import traveller.composeapp.generated.resources.process_payment
import traveller.composeapp.generated.resources.starting_place
import traveller.composeapp.generated.resources.total_payment

@Composable
fun TripItemPayingScreen(
    onBackPressed: () -> Unit,
    viewModel: ShoppingItemDetailsViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    val details = listOf(
        ShoppingDetailModel(
            title = stringResource(Res.string.initial_payment),
            description = formatMoney(state.value.initialPayment),
            icon = Res.drawable.ic_cash
        ),
        ShoppingDetailModel(
            title = stringResource(Res.string.total_payment),
            description = formatMoney(state.value.totalPayment),
            icon = Res.drawable.ic_cash
        ),
        ShoppingDetailModel(
            title = stringResource(Res.string.starting_place),
            description = state.value.meetingPoint,
            icon = Res.drawable.ic_address
        ),
        ShoppingDetailModel(
            title = stringResource(Res.string.leaving_date),
            description = state.value.leavingTime,
            icon = Res.drawable.ic_address
        ),
    )

    LazyColumn(
        Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            TripItemPayHeader(state.value) { onBackPressed() }
        }
        item {
            TripItemPayDetail(details)
        }
        item {
            ShoppingPaymentWay(state = state.value) { event ->
                viewModel.handleEvent(event)
            }
        }
        item {
            Box(Modifier.padding(top = 16.dp)) {
                ActionButton(
                    text = stringResource(Res.string.process_payment),
                    modifier = Modifier.fillMaxWidth()
                ) {

                }
            }
        }
    }
}