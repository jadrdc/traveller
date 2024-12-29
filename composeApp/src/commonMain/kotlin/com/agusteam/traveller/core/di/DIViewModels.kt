package com.agusteam.traveller.core.di

import com.agusteam.traveller.presenter.explore.viewmodels.ExploreViewModel
import com.agusteam.traveller.presenter.home.viewmodel.HomeViewModel
import com.agusteam.traveller.presenter.orders.viewmodels.OrderDetailViewModel
import com.agusteam.traveller.presenter.orders.viewmodels.OrderHistoryViewModel
import com.agusteam.traveller.presenter.profile.viewmodels.ProfileViewModel
import com.agusteam.traveller.presenter.profile.viewmodels.TripProviderViewModel
import com.agusteam.traveller.presenter.shopping.viewmodels.ShoppingItemDetailsViewModel
import com.agusteam.traveller.presenter.signup.viewmodels.LoginViewModel
import com.agusteam.traveller.presenter.signup.viewmodels.SignUpViewModel
import com.agusteam.traveller.presenter.wishlist.viewmodels.WishListItemViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single<SignUpViewModel> { SignUpViewModel(get(), get()) }
    single<LoginViewModel> { LoginViewModel(get(), get(), get(), get()) }
    single<ExploreViewModel> { ExploreViewModel(get(), get(), get(), get(), get()) }
    single<ProfileViewModel> { ProfileViewModel(get(), get()) }
    single<OrderHistoryViewModel> { OrderHistoryViewModel() }
    single<ShoppingItemDetailsViewModel> { ShoppingItemDetailsViewModel(get(), get(), get()) }
    single<OrderDetailViewModel> { OrderDetailViewModel() }
    single<WishListItemViewModel> { WishListItemViewModel() }
    single<HomeViewModel> { HomeViewModel() }
    single<TripProviderViewModel> { TripProviderViewModel(get()) }
}