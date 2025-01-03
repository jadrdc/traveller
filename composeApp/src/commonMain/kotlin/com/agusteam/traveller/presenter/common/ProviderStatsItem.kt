package com.agusteam.traveller.presenter.common


import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.agusteam.traveller.domain.models.TripProviderModel
import com.agusteam.traveller.presenter.getTimePeriod
import com.agusteam.traveller.presenter.getTimePeriodUnit
import org.jetbrains.compose.resources.stringResource
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.current_trips
import traveller.composeapp.generated.resources.trip_offered

@Composable
fun ProviderStatsItem(tripProviderModel: TripProviderModel) {
    Column(
        modifier = Modifier
    ) {
        ProviderStatItem(
            value = tripProviderModel.totalOfferedTrips.toString(),
            label = stringResource(Res.string.trip_offered)
        )
        ProviderStatItem(
            value = tripProviderModel.currentItems,
            label = stringResource(Res.string.current_trips)
        )
        ProviderStatItem(
            showDivider = false,
            value = getTimePeriodUnit(tripProviderModel.month),
            label = getTimePeriod(tripProviderModel.month)
        )
    }
}