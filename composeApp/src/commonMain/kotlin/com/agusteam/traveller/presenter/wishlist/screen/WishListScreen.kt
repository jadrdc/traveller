package com.agusteam.traveller.presenter.wishlist.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.agusteam.traveller.domain.models.TripModel
import com.agusteam.traveller.presenter.common.ErrorState
import com.agusteam.traveller.presenter.wishlist.composable.WishListItemSectionLoading
import com.agusteam.traveller.presenter.wishlist.composable.WishListSection
import com.agusteam.traveller.presenter.wishlist.viewmodels.WishListItemViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WishListScreen(viewModel: WishListItemViewModel = koinViewModel(), goDetails: (TripModel) -> Unit) {

    LaunchedEffect(Unit) {
        viewModel.handleEvent(WishListItemViewModel.WishListEvent.WishListLoad("0d7f08cf-9a8d-47a3-9a30-37793374a2be"))
    }

    val state = viewModel.state.collectAsStateWithLifecycle()
    if (state.value.errorState) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ErrorState()
        }
    } else {
        if (state.value.isLoading) {
            WishListItemSectionLoading()
        } else {

            WishListSection(state.value.favoriteItems, goDetails)
        }
    }
}