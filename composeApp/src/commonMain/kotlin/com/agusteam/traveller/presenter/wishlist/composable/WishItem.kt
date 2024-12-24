package com.agusteam.traveller.presenter.wishlist.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.agusteam.traveller.domain.models.TripModel
import com.agusteam.traveller.presenter.common.FavoriteButton
import com.agusteam.traveller.presenter.theme.CustomFontFamily
import com.agusteam.traveller.presenter.theme.grey500
import com.agusteam.traveller.presenter.theme.primary
import com.agusteam.traveller.presenter.theme.secondary

@Composable
fun WishItem(
    item: TripModel, goDetails: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { goDetails() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier.size(64.dp).clip(RoundedCornerShape(8.dp)),
                model = item.image,
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
            Column {
                Text(
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier,
                    text = item.name,
                    color = secondary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "$${item.price}",
                    color = primary,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = CustomFontFamily(),
                    fontSize = 14.sp
                )
                Text(
                    modifier = Modifier,
                    text = item.date,
                    color = grey500,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
        FavoriteButton(isSavedForLater = true, icon = 32.dp, size = 32.dp) {

        }
    }
}