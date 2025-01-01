package com.agusteam.traveller.presenter.wishlist.state

import com.agusteam.traveller.core.base.ViewModelState
import com.agusteam.traveller.presenter.common.ItemProviderState

data class WishListOrderDetailState(
    val itemProviderState: ItemProviderState = ItemProviderState("", "", 0),
    val isLoadingContent: Boolean = false, val includedServices: List<String> = listOf(),
) : ViewModelState
