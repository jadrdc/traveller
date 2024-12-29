package com.agusteam.traveller.presenter.signup.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.agusteam.traveller.presenter.common.ActionButton
import com.agusteam.traveller.presenter.common.EditInputField
import com.agusteam.traveller.presenter.common.ErrorModal
import com.agusteam.traveller.presenter.common.NavigationBar
import com.agusteam.traveller.presenter.common.ObserveAsEvents
import com.agusteam.traveller.presenter.signup.viewmodels.SignUpViewModel
import com.agusteam.traveller.presenter.theme.primary
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.confirm_password
import traveller.composeapp.generated.resources.email
import traveller.composeapp.generated.resources.finish_signup
import traveller.composeapp.generated.resources.lastname
import traveller.composeapp.generated.resources.name
import traveller.composeapp.generated.resources.password
import traveller.composeapp.generated.resources.phone
import traveller.composeapp.generated.resources.signup
import traveller.composeapp.generated.resources.terms


@Composable
fun SignUpAccountScreen(
    viewModel: SignUpViewModel = koinViewModel(),
    onBackPressed: () -> Unit = {}
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val event = viewModel.events
    ObserveAsEvents(event) { event ->

    }
    ErrorModal(title = state.errorModel?.title ?: "",
        message = state.errorModel?.message ?: "",
        showError = state.errorModel != null, onDismiss = {
            viewModel.onEventHandler(SignUpViewModel.SignUpEvent.ClearError)
        })
    Box {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    NavigationBar(title = stringResource(Res.string.finish_signup)) { onBackPressed() }
                }
                item {
                    Box(Modifier.padding(top = 16.dp)) {
                        EditInputField(
                            query = state.name,
                            labelText = stringResource(Res.string.name),
                            onQueryChange = {
                                viewModel.onEventHandler(
                                    SignUpViewModel.SignUpEvent.OnNameChanged(
                                        it
                                    )
                                )
                            }, modifier = Modifier
                        )
                    }
                }
                item {
                    EditInputField(
                        query = state.lastName,
                        labelText = stringResource(Res.string.lastname),
                        onQueryChange = {
                            viewModel.onEventHandler(
                                SignUpViewModel.SignUpEvent.OnLastNameChanged(
                                    it
                                )
                            )
                        }, modifier = Modifier
                    )
                }
                item {
                    EditInputField(
                        usePhoneMask = true,
                        errorText = state.phoneError,
                        error = state.isPhoneError,
                        keyboardType = KeyboardType.Phone,
                        query = state.phone,
                        labelText = stringResource(Res.string.phone),
                        onQueryChange = {
                            viewModel.onEventHandler(
                                SignUpViewModel.SignUpEvent.OnPhoneNumberChanged(
                                    it
                                )
                            )
                        }, modifier = Modifier
                    )
                }
                item {
                    EditInputField(
                        query = state.email,
                        errorText = state.emailError,
                        error = state.isEmailError,
                        labelText = stringResource(Res.string.email),
                        onQueryChange = {
                            viewModel.onEventHandler(SignUpViewModel.SignUpEvent.OnEmailChanged(it))
                        }, modifier = Modifier
                    )
                }
                item {
                    EditInputField(
                        keyboardType = KeyboardType.Password,
                        query = state.password,
                        labelText = stringResource(Res.string.password),
                        error = state.isPasswordError,
                        onQueryChange = {
                            viewModel.onEventHandler(
                                SignUpViewModel.SignUpEvent.OnPasswordChanged(
                                    it
                                )
                            )
                        },
                        modifier = Modifier,
                        errorText = state.passwordError,
                    )
                }
                item {
                    EditInputField(
                        keyboardType = KeyboardType.Password,
                        query = state.confirmPassword,
                        error = state.isSamePasswordError,
                        labelText = stringResource(Res.string.confirm_password),
                        onQueryChange = {
                            viewModel.onEventHandler(
                                SignUpViewModel.SignUpEvent.OnConfirmPasswordChanged(
                                    it
                                )
                            )
                        },
                        modifier = Modifier,
                        errorText = state.samePasswordError,
                    )
                }

                item {
                    Text(
                        text = stringResource(Res.string.terms),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                }
            }
            ActionButton(
                isValid = state.isValid(),
                text = stringResource(Res.string.signup),
                modifier = Modifier.fillMaxWidth()
            ) {
                viewModel.onEventHandler(SignUpViewModel.SignUpEvent.SignUp)
            }
        }
        // Loading overlay
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(enabled = false) {}, // Prevents interaction
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = primary)
            }
        }
    }
}

