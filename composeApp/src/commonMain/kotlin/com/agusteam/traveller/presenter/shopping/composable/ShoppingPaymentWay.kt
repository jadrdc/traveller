package com.agusteam.traveller.presenter.shopping.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agusteam.traveller.domain.models.PaymentModel
import com.agusteam.traveller.presenter.common.CustomRadioButton
import com.agusteam.traveller.presenter.formatMoney
import com.agusteam.traveller.presenter.shopping.state.TripDetailState
import com.agusteam.traveller.presenter.shopping.viewmodels.ShoppingItemDetailsViewModel
import com.agusteam.traveller.presenter.theme.secondary
import org.jetbrains.compose.resources.stringResource
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.booking_payment
import traveller.composeapp.generated.resources.booking_payment_description
import traveller.composeapp.generated.resources.payment_to_pay
import traveller.composeapp.generated.resources.total_payment
import traveller.composeapp.generated.resources.total_payment_description

@Composable
fun ShoppingPaymentWay(
    state: TripDetailState,
    onEvent: (ShoppingItemDetailsViewModel.ShoppingDetailEvent) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = stringResource(Res.string.payment_to_pay),
            color = secondary,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        CustomRadioButton(
            title = stringResource(Res.string.total_payment),
            description = stringResource(
                Res.string.total_payment_description,
                formatMoney(state.totalPayment)
            ),
            isSelected = PaymentModel.TOTAL_PAYMENT == state.selectedPaymentType
        ) {
            onEvent(
                ShoppingItemDetailsViewModel.ShoppingDetailEvent.OnPaymentTypePicked(
                    PaymentModel.TOTAL_PAYMENT
                )
            )
        }
        CustomRadioButton(
            title = stringResource(Res.string.booking_payment),
            description = stringResource(
                Res.string.booking_payment_description,
                formatMoney(state.initialPayment)
            ),
            isSelected = PaymentModel.BOOKING_PAYMENT == state.selectedPaymentType
        ) {
            onEvent(
                ShoppingItemDetailsViewModel.ShoppingDetailEvent.OnPaymentTypePicked(
                    PaymentModel.BOOKING_PAYMENT
                )
            )

        }
    }
}