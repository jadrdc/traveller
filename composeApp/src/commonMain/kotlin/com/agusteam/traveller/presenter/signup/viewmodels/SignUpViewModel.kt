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

    private suspend fun signUp() {
        setState { copy(isLoading = true) }
        when (val result = signUpUseCase(
            state.value.name,
            state.value.lastName,
            state.value.phone,
            state.value.email,
            state.value.password
        )) {
            is OperationResult.Error -> {
                onErrorHappened(
                    true,
                    "Error al registrar la cuenta",
                    message = result.exception.message ?: " "
                )
            }

            is OperationResult.Success -> {
                onErrorHappened(
                    true,
                    "Confirmación enviada",
                    "¡Cuenta creada con éxito! Hemos enviado un correo de confirmación a tu dirección de correo electrónico. Por favor, revisa tu bandeja de entrada y confirma tu cuenta."
                )
            }
        }
        setState { copy(isLoading = false) }

    }

    private suspend fun onNameChanged(value: String) {
        setState {
            copy(name = value)
        }
    }

    private suspend fun onLastNameChanged(value: String) {
        setState {
            copy(lastName = value)
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

    private suspend fun onPhoneChanged(value: String) {
        setState {
            copy(phone = value)
        }
        setValidationError(ValidatorType.PHONE, value)
    }

    private suspend fun setValidationError(
        validatorType: ValidatorType, value: String, value2: String = ""
    ) {
        val result = validator.validateField(validatorType, value = value, value2 = value2)
        when (result.validatorType) {
            ValidatorType.EMAIL -> {
                setState {
                    copy(
                        emailError = result.errorMessage, isEmailError = result.isError
                    )
                }
            }

            ValidatorType.PHONE -> {
                setState {
                    copy(
                        phoneError = result.errorMessage, isPhoneError = result.isError
                    )

                }
            }

            ValidatorType.PASSWORD -> {
                if (!result.isError) {
                    setValidationError(ValidatorType.PASSWORD_SAME, value, value2)
                }
                setState {
                    copy(
                        passwordError = result.errorMessage, isPasswordError = result.isError
                    )
                }
            }

            ValidatorType.PASSWORD_SAME -> {
                setState {
                    copy(
                        samePasswordError = result.errorMessage,
                        isSamePasswordError = result.isError
                    )
                }
            }
        }

    }

    private suspend fun onEmailChanged(value: String) {
        setState {
            copy(email = value)
        }
        setValidationError(ValidatorType.EMAIL, value)
    }

    private suspend fun onPasswordChanged(value: String) {
        setState {
            copy(password = value)
        }
        setValidationError(ValidatorType.PASSWORD, value)
    }

    private suspend fun onPasswordConfirmChanged(value: String) {
        setState {
            copy(confirmPassword = value)
        }
        setValidationError(ValidatorType.PASSWORD_SAME, state.value.password, value)
    }

    fun onEventHandler(event: SignUpEvent) {
        viewModelScope.launch {
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
                    onErrorHappened(false)
                }
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
