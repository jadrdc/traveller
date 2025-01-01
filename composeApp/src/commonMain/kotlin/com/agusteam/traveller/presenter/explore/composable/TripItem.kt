package com.agusteam.traveller.presenter.explore.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.agusteam.traveller.domain.models.TripModel
import com.agusteam.traveller.presenter.common.FavoriteButton
import com.agusteam.traveller.presenter.explore.viewmodels.ExploreEvent
import com.agusteam.traveller.presenter.formatMoney
import com.agusteam.traveller.presenter.theme.CustomFontFamily
import com.agusteam.traveller.presenter.theme.grey500
import com.agusteam.traveller.presenter.theme.primary
import com.agusteam.traveller.presenter.theme.secondary

@Composable
fun TripItem(
    item: TripModel,
    onClick: () -> Unit,
    toggleFavorite: (ExploreEvent) -> Unit
) {
    Column(Modifier.clickable { onClick() }) {
        Box {
            AsyncImage(
                modifier = Modifier.fillMaxWidth().height(290.dp).clip(RoundedCornerShape(16.dp)),
                model = item.images.firstOrNull() ?: "",
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
            Box(
                Modifier.align(
                    Alignment.TopEnd
                ).padding(16.dp)
            ) {
                FavoriteButton(isSavedForLater = item.isSavedForLater) {
                    toggleFavorite(
                        ExploreEvent.OnShoppingItemMarked(item)
                    )
                }
            }
        }
        Column(Modifier) {
            Row(
                Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.name,
                    color = secondary,
                    modifier = Modifier.padding(top = 8.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(item.categoryList) { category ->
                        AsyncImage(
                            model = category.imageUrl,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.size(32.dp),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(primary)
                        )
                    }
                }
            }
            Text(
                text = item.date,
                color = grey500,
                modifier = Modifier,
                style = MaterialTheme.typography.bodyMedium // Use the appropriate typography style

            )
            Text(
                text = formatMoney(item.price),
                color = secondary,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier,
                fontWeight = FontWeight.SemiBold,
                fontFamily = CustomFontFamily(),
                fontSize = 14.sp
            )
        }
    }
}