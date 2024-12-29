package com.agusteam.traveller.presenter.shopping.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.agusteam.traveller.presenter.common.TripItemBar

@Composable
fun ShoppingItemHeader(
    images: List<String>,
    isSavedForLater: Boolean,
    onBackPressed: () -> Unit = {},
    onCLick: () -> Unit = {}
) {
    val pagerState = rememberPagerState(pageCount = {
        images.size
    })
    Box {
        Box {
            HorizontalPager(state = pagerState) { page ->
                AsyncImage(
                    modifier = Modifier.fillMaxWidth().height(290.dp),
                    model = images[page],
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null
                )
            }
            Box(
                Modifier.background(Color.Black, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp)).align(
                        Alignment.BottomEnd
                    )
            ) {
                Text(
                    text = "${pagerState.currentPage + 1}/${images.size}",
                    modifier = Modifier.padding(8.dp),
                    color = Color.White
                )
            }
        }
        Box(Modifier.padding(16.dp)) { TripItemBar(isSavedForLater, onBackPressed, onCLick) }
    }

}