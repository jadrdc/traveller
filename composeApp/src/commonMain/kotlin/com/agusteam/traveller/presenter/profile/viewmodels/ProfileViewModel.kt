package com.agusteam.traveller.presenter.profile.viewmodels

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.core.base.GenericViewModel
import com.agusteam.traveller.data.util.EMAIL
import com.agusteam.traveller.data.util.LAST_NAME
import com.agusteam.traveller.data.util.NAME
import com.agusteam.traveller.data.util.PHONE
import com.agusteam.traveller.domain.usecase.GetProfileUseCase
import com.agusteam.traveller.presenter.formatPhone
import com.agusteam.traveller.presenter.profile.state.ProfileState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileViewModel(val getProfileUseCase: GetProfileUseCase) :
    GenericViewModel<ProfileState, ProfileEvent>(ProfileState()) {

    init {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            getProfileUseCase().mapLatest { preference ->
                val nameKey = stringPreferencesKey(NAME)
                val lastNameKey = stringPreferencesKey(LAST_NAME)
                val emailKey = stringPreferencesKey(EMAIL)
                val phoneKey = stringPreferencesKey(PHONE)

                val name = preference[nameKey] ?: ""
                val lastNam = preference[lastNameKey] ?: ""
                val email = preference[emailKey] ?: ""
                val phone = preference[phoneKey] ?: ""

                updateState {
                    copy(name = name, lastname = lastNam, email = email, phone = formatPhone(phone))
                }
            }.launchIn(viewModelScope)
            updateState { copy(isLoading = false) }
        }
    }

}

sealed interface ProfileEvent