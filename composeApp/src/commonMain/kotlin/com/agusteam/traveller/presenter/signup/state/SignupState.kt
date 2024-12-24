package com.agusteam.traveller.presenter.signup.state

import com.agusteam.traveller.core.base.ViewModelState
import com.agusteam.traveller.domain.models.ErrorModel

data class SignupState(
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val isEmailError: Boolean = false,
    val emailError: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val phone: String = "",
    val passwordError: String = "",
    val isPasswordError: Boolean = false,
    val samePasswordError: String = "",
    val isSamePasswordError: Boolean = false,
    val phoneError: String = "",
    val isPhoneError: Boolean = false,
    val isLoading: Boolean = false,
    val errorModel: ErrorModel? = null
) : ViewModelState {
    fun isValid(): Boolean {
        return name.isNotBlank() &&
                lastName.isNotBlank() &&
                email.isNotBlank() &&
                password.isNotBlank() &&
                confirmPassword == password &&
                phone.isNotBlank() && phone.length == 10
    }
}