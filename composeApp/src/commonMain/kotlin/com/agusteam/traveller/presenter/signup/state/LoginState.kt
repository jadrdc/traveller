package com.agusteam.traveller.presenter.signup.state

import com.agusteam.traveller.core.base.ViewModelState
import com.agusteam.traveller.domain.models.ErrorModel

data class LoginState(
    val email: String = "",
    val isEmailError: Boolean = false,
    val emailError: String = "",
    val password: String = "",
    val passwordError: String = "",
    val isPasswordError: Boolean = false,
    val isLoading: Boolean = false,
    val errorModel: ErrorModel? = null

) : ViewModelState {
    fun isValid(): Boolean {
        return email.isNotBlank() && password.isNotBlank() && !isEmailError && !isPasswordError
    }
}
