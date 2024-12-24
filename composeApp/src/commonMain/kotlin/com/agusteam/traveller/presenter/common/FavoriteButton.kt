package com.agusteam.traveller.presenter.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.ic_favorite
import traveller.composeapp.generated.resources.ic_favorite_filled

@Composable
fun FavoriteButton(
    size: Dp = 24.dp,
    icon:Dp=16.dp,
    isSavedForLater: Boolean, onFavoriteToggle: () -> Unit
) {
    Box(Modifier.clip(CircleShape).size(size).background(Color.White, CircleShape)) {
        Icon(
            tint = if (isSavedForLater) Color.Red else Color.Black,
            modifier = Modifier.size(icon)
                .clickable(interactionSource = null, indication = null) {

                    onFavoriteToggle()
                }.align(Alignment.Center),
            contentDescription = null,
            painter = painterResource(
                if (isSavedForLater) Res.drawable.ic_favorite_filled
                else Res.drawable.ic_favorite
            ),
        )
    }
}