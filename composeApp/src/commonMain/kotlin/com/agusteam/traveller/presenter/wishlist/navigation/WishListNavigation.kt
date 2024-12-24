package com.agusteam.traveller.presenter.wishlist.navigation

sealed class WishListNavigation(val route: String) {
    data object WishListScreen : WishListNavigation("wish_list_screen")
    data object WishListItemDetailScreen : WishListNavigation("wish_list_item_detail_screen")
}