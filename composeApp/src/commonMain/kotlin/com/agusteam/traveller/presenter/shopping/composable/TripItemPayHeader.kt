package com.agusteam.traveller.presenter.shopping.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.agusteam.traveller.presenter.common.NavigationBar
import com.agusteam.traveller.presenter.shopping.state.TripDetailState
import com.agusteam.traveller.presenter.theme.grey500
import com.agusteam.traveller.presenter.theme.secondary
import org.jetbrains.compose.resources.stringResource
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.pay_trip

@Composable
fun TripItemPayHeader(
    state: TripDetailState,
    onBackPressed: () -> Unit
) {

    Column {
        NavigationBar(title = stringResource(Res.string.pay_trip)) { onBackPressed() }
        Row(
            modifier = Modifier.padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier.size(120.dp).clip(RoundedCornerShape(8.dp)),
                model = state.details.galleryPhotos[0],
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
            Column {
                Text(
                    modifier = Modifier,
                    text = state.title,
                    color = secondary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    modifier = Modifier,
                    text = state.details.destiny,
                    color = grey500,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(top = 16.dp),
        )
    }
}