package com.agusteam.traveller.presenter.signup.viewmodels

import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.core.base.GenericViewModel
import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.models.ErrorModel
import com.agusteam.traveller.domain.models.LoginModel
import com.agusteam.traveller.domain.usecase.LoginUseCase
import com.agusteam.traveller.domain.usecase.RequestResetPasswordEmailUseCase
import com.agusteam.traveller.domain.validators.FieldValidator
import com.agusteam.traveller.domain.validators.ValidatorType
import com.agusteam.traveller.presenter.signup.state.LoginState
import kotlinx.coroutines.launch

class LoginViewModel(
    private val validator: FieldValidator,
    private val useCase: LoginUseCase,
    private val requestResetPasswordEmailUseCase: RequestResetPasswordEmailUseCase
) : GenericViewModel<LoginState, LoginEvent>(LoginState()) {


    private fun setValidationError(
        validatorType: ValidatorType,
        value: String,
        value2: String = ""
    ) {
        val result = validator.validateField(validatorType, value = value, value2 = value2)
        when (result.validatorType) {
            ValidatorType.EMAIL -> {
                updateState {
                    copy(
                        emailError = result.errorMessage, isEmailError = result.isError
                    )
                }
            }

            ValidatorType.PHONE, ValidatorType.PASSWORD, ValidatorType.PASSWORD_SAME -> {

            }
        }

    }

    private fun onEmailChanged(value: String) {
        updateState {
            setValidationError(ValidatorType.EMAIL, value)
            copy(email = value)
        }
    }

    private fun onPasswordChanged(value: String) {
        updateState {
            copy(password = value)
        }
    }

    private fun onErrorHappened(value: Boolean, title: String = "", message: String = "") {
        val errorModel = if (!value) {
            null
        } else {
            ErrorModel(title = title, message = message)
        }
        updateState {
            copy(
                errorModel = errorModel
            )
        }
    }

    private fun login() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            when (val result = useCase(state.value.email, state.value.password)) {
                is OperationResult.Error -> {
                    onErrorHappened(
                        true,
                        "Error de Inicio de Sesión",
                        result.exception.message ?: ""
                    )
                }

                is OperationResult.Success -> {
                    val userModel = result.data
                    sendEvent(LoginEvent.OnUserLogon(userModel))
                }
            }
            updateState { copy(isLoading = false) }
        }
    }

    private fun requestEmail() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            when (val result = requestResetPasswordEmailUseCase(state.value.email)) {
                is OperationResult.Error -> {
                    onErrorHappened(
                        true,
                        "Error al reiniciar la contraseña",
                        "No se pudo enviar el email de reinicio de contraseña."
                    )
                }

                is OperationResult.Success -> {
                    onErrorHappened(
                        true,
                        "Reinicio de contraseña ",
                        "Email enviado"
                    )
                }
            }
            updateState { copy(isLoading = false) }
        }
    }

    private fun onPasswordReset() {
        viewModelScope.launch {
            val result = validator.validateField(ValidatorType.EMAIL, value = state.value.email)
            if (result.isError || state.value.email.isBlank()) {
                onErrorHappened(
                    true,
                    "Error al reiniciar la contraseña",
                    "No se pudo enviar el email de reinicio de contraseña.el campo correo no puede ser invalido"
                )
            } else {
                requestEmail()
            }
        }
    }

    fun onEventHandler(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnPasswordChanged -> {
                onPasswordChanged(event.value)
            }

            is LoginEvent.OnEmailChanged -> {
                onEmailChanged(event.value)
            }

            is LoginEvent.OnLoginProcess -> {
                login()
            }

            is LoginEvent.ClearErrorLogin -> {
                onErrorHappened(false)
            }

            is LoginEvent.OnClickPasswordForgot -> {
                onPasswordReset()
            }

            else -> {

            }
        }
    }
}

sealed interface LoginEvent {
    data object OnLoginProcess : LoginEvent
    data class OnUserLogon(val user: LoginModel) : LoginEvent
    class OnEmailChanged(val value: String) : LoginEvent
    class OnPasswordChanged(val value: String) : LoginEvent
    data object ClearErrorLogin : LoginEvent
    data object OnClickPasswordForgot : LoginEvent
}