package com.agusteam.traveller.presenter.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agusteam.traveller.presenter.theme.secondary
import org.jetbrains.compose.resources.stringResource
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.location

@Composable
fun MapDetails(lat: Double, lng: Double) {
    Column(Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = stringResource(Res.string.location),
            color = secondary,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        MapComponent(Modifier.padding(top = 16.dp), lat = lat, lng = lng)
        HorizontalDivider(
            modifier = Modifier.padding(top = 16.dp),
        )
    }
}