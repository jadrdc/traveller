package com.agusteam.traveller.presenter.wishlist.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.agusteam.traveller.presenter.wishlist.composable.WishItem
import com.agusteam.traveller.presenter.wishlist.viewmodels.WishListItemViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.favorite

@Composable
fun WishListScreen(viewModel: WishListItemViewModel = koinViewModel(), goDetails: () -> Unit) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Column(Modifier.padding(horizontal = 16.dp, vertical = 32.dp)) {
        Text(
            text = stringResource(Res.string.favorite),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            items(state.value.favoriteItems) { item ->
                WishItem(item) {
                    goDetails()
                }
            }
        }
    }
}