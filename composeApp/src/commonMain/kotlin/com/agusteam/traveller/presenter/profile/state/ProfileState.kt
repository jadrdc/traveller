package com.agusteam.traveller.presenter.profile.state

import com.agusteam.traveller.core.base.ViewModelState

data class ProfileState(
    val isLoading: Boolean = false,
    val userProfileName: String = "",
    val name: String = "",
    val lastname: String = "",
    val email: String = "",
    val phone: String = "",
) : ViewModelState {
    val fullName: String
        get() = "$name $lastname"
}
