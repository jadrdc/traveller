package com.agusteam.traveller.presenter.profile.viewmodels

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.core.base.GenericViewModel
import com.agusteam.traveller.data.util.EMAIL
import com.agusteam.traveller.data.util.LAST_NAME
import com.agusteam.traveller.data.util.NAME
import com.agusteam.traveller.data.util.PHONE
import com.agusteam.traveller.domain.models.ErrorModel
import com.agusteam.traveller.domain.usecase.GetProfileUseCase
import com.agusteam.traveller.domain.usecase.LogoutUseCase
import com.agusteam.traveller.presenter.formatPhone
import com.agusteam.traveller.presenter.profile.state.ProfileState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileViewModel(val getProfileUseCase: GetProfileUseCase, val logoutUseCase: LogoutUseCase) :
    GenericViewModel<ProfileState, ProfileEvent>(ProfileState()) {

    init {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            getProfileUseCase().mapLatest { preference ->
                val nameKey = stringPreferencesKey(NAME)
                val lastNameKey = stringPreferencesKey(LAST_NAME)
                val emailKey = stringPreferencesKey(EMAIL)
                val phoneKey = stringPreferencesKey(PHONE)

                val name = preference[nameKey] ?: ""
                val lastNam = preference[lastNameKey] ?: ""
                val email = preference[emailKey] ?: ""
                val phone = preference[phoneKey] ?: ""

                setState {
                    copy(name = name, lastname = lastNam, email = email, phone = formatPhone(phone))
                }
            }.launchIn(viewModelScope)
            setState { copy(isLoading = false) }
        }
    }

    fun handleEvent(event: ProfileEvent) {
        viewModelScope.launch {
            when (event) {
                ProfileEvent.LogoutUser -> {
                    logout()
                }

                ProfileEvent.OnErrorModalAccepted -> {
                    onErrorHappened(false)
                }

                else -> {

                }
            }
        }
    }

    private suspend fun logout() {
        try {
            logoutUseCase()
            sendEvent(ProfileEvent.UserSessionClosed)
        } catch (e: Exception) {
            onErrorHappened(
                true,
                "Error Inesperado",
                "No se pudo completar la operacion,intente mas tarde."
            )
        }
    }

    private suspend fun onErrorHappened(value: Boolean, title: String = "", message: String = "") {
        val errorModel = if (!value) {
            null
        } else {
            ErrorModel(title = title, message = message)
        }
        setState {
            copy(
                errorModel = errorModel
            )
        }
    }
}

sealed interface ProfileEvent {
    data object ProfileLoaded : ProfileEvent
    data object LogoutUser : ProfileEvent
    data object UserSessionClosed : ProfileEvent
    data object OnErrorModalAccepted : ProfileEvent

}