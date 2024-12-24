package com.agusteam.traveller.presenter.profile.viewmodels

import androidx.lifecycle.ViewModel
import com.agusteam.traveller.presenter.profile.state.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {
    private var _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()
}