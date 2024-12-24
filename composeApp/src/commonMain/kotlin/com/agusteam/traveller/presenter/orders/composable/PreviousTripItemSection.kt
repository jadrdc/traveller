package com.agusteam.traveller.presenter.orders.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agusteam.traveller.domain.models.TripModel
import org.jetbrains.compose.resources.stringResource
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.old

@Composable
fun PreviousTripItemSection(
    modifier: Modifier = Modifier.padding(top = 32.dp),
    oldItems: List<TripModel>, goDetails: () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(Res.string.old),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        oldItems.forEach { item ->
            PreviousTripItem(item) { goDetails() }

        }
    }
}