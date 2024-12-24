package com.agusteam.traveller.presenter.signup.viewmodels

import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.core.base.GenericViewModel
import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.models.ErrorModel
import com.agusteam.traveller.domain.usecase.SignUpUseCase
import com.agusteam.traveller.domain.validators.FieldValidator
import com.agusteam.traveller.domain.validators.ValidatorType
import com.agusteam.traveller.presenter.signup.state.SignupState
import kotlinx.coroutines.launch


class SignUpViewModel(
    private val validator: FieldValidator,
    private val signUpUseCase: SignUpUseCase,
) : GenericViewModel<SignupState, SignUpViewModel.SignUpEvent>(SignupState()) {

    private fun signUp() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            when (val result = signUpUseCase(
                state.value.name,
                state.value.lastName,
                state.value.phone,
                state.value.email,
                state.value.password
            )) {
                is OperationResult.Error -> {
                    OnErrorHappened(
                        true,
                        "Error al registrar la cuenta",
                        message = result.exception.message ?: " "
                    )
                }

                is OperationResult.Success -> {
                    OnErrorHappened(
                        true,
                        "Confirmación enviada",
                        "¡Cuenta creada con éxito! Hemos enviado un correo de confirmación a tu dirección de correo electrónico. Por favor, revisa tu bandeja de entrada y confirma tu cuenta."
                    )
                }
            }
            updateState { copy(isLoading = false) }
        }
    }

    private fun onNameChanged(value: String) {
        updateState {
            copy(name = value)
        }
    }

    private fun onLastNameChanged(value: String) {
        updateState {
            copy(lastName = value)
        }
    }

    private fun OnErrorHappened(value: Boolean, title: String = "", message: String = "") {
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

    private fun onPhoneChanged(value: String) {
        updateState {
            setValidationError(ValidatorType.PHONE, value)
            copy(phone = value)
        }
    }

    private fun setValidationError(
        validatorType: ValidatorType, value: String, value2: String = ""
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

            ValidatorType.PHONE -> {
                updateState {
                    copy(
                        phoneError = result.errorMessage, isPhoneError = result.isError
                    )

                }
            }

            ValidatorType.PASSWORD -> {
                updateState {
                    if (!result.isError) {
                        setValidationError(ValidatorType.PASSWORD_SAME, value, value2)
                    }
                    copy(
                        passwordError = result.errorMessage, isPasswordError = result.isError
                    )
                }
            }

            ValidatorType.PASSWORD_SAME -> {
                updateState {
                    copy(
                        samePasswordError = result.errorMessage,
                        isSamePasswordError = result.isError
                    )
                }
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
            setValidationError(ValidatorType.PASSWORD, value)
            copy(password = value)
        }
    }

    private fun onPasswordConfirmChanged(value: String) {
        updateState {
            setValidationError(ValidatorType.PASSWORD_SAME, state.value.password, value)
            copy(confirmPassword = value)
        }
    }

    fun onEventHandler(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.OnConfirmPasswordChanged -> {
                onPasswordConfirmChanged(event.value)
            }

            is SignUpEvent.OnEmailChanged -> {
                onEmailChanged(event.value)
            }

            is SignUpEvent.OnLastNameChanged -> {
                onLastNameChanged(event.value)
            }

            is SignUpEvent.OnNameChanged -> {
                onNameChanged(event.value)
            }

            is SignUpEvent.OnPasswordChanged -> {
                onPasswordChanged(event.value)
            }

            is SignUpEvent.OnPhoneNumberChanged -> {
                onPhoneChanged(event.value)
            }

            is SignUpEvent.SignUp -> {
                signUp()
            }

            is SignUpEvent.ClearError -> {
                OnErrorHappened(false)
            }
        }
    }

    sealed interface SignUpEvent {
        data object SignUp : SignUpEvent
        data object ClearError : SignUpEvent
        class OnNameChanged(val value: String) : SignUpEvent
        class OnLastNameChanged(val value: String) : SignUpEvent
        class OnPhoneNumberChanged(val value: String) : SignUpEvent
        class OnEmailChanged(val value: String) : SignUpEvent
        class OnPasswordChanged(val value: String) : SignUpEvent
        class OnConfirmPasswordChanged(val value: String) : SignUpEvent
    }
}
