package com.agusteam.traveller.presenter.home.model


import org.jetbrains.compose.resources.DrawableResource
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.ic_explore
import traveller.composeapp.generated.resources.ic_profile
import traveller.composeapp.generated.resources.ic_trip
import traveller.composeapp.generated.resources.ic_wish_list

sealed class BottomNavigationItem(
    val title: String,
    val drawableResource: DrawableResource,
    val route: String
) {
    data object Explore : BottomNavigationItem("Explorar", Res.drawable.ic_explore, "home_screen")
    data object WishList :
        BottomNavigationItem("Favoritos", Res.drawable.ic_wish_list, "wishlist_screen")

    data object OrderHistoric :
        BottomNavigationItem("Viajes", Res.drawable.ic_trip, "order_history_screen")

    data object Profile : BottomNavigationItem("Perfil", Res.drawable.ic_profile, "profile_screen")
}