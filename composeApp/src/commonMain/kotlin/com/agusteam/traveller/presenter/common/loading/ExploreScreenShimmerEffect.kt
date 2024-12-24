package com.agusteam.traveller.presenter.common.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.agusteam.traveller.presenter.common.effects.shimmerEffect
import com.agusteam.traveller.presenter.common.loading.CategoryLoadingList
import com.agusteam.traveller.presenter.common.loading.TripItemLoading

@Composable
fun ExploreScreenShimmerEffect() {

    Column(
        Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(45.dp).clip(RoundedCornerShape(16.dp))
                .shimmerEffect(),
        ) {

        }
        CategoryLoadingList()
        repeat(4) {
            TripItemLoading()
        }
    }
}