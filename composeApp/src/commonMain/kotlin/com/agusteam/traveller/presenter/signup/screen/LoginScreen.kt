package com.agusteam.traveller.presenter.signup.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.agusteam.traveller.domain.models.LoginModel
import com.agusteam.traveller.presenter.common.ActionButton
import com.agusteam.traveller.presenter.common.EditInputField
import com.agusteam.traveller.presenter.common.ErrorModal
import com.agusteam.traveller.presenter.common.ObserveAsEvents
import com.agusteam.traveller.presenter.signup.viewmodels.LoginEvent
import com.agusteam.traveller.presenter.signup.viewmodels.LoginEvent.OnUserLogon
import com.agusteam.traveller.presenter.signup.viewmodels.LoginViewModel
import com.agusteam.traveller.presenter.theme.primary
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.create_account
import traveller.composeapp.generated.resources.email
import traveller.composeapp.generated.resources.forgot_password
import traveller.composeapp.generated.resources.loading
import traveller.composeapp.generated.resources.login
import traveller.composeapp.generated.resources.password


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLogin: (LoginModel) -> Unit = {},
    onSignUp: () -> Unit = {},
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val event = viewModel.events
    ObserveAsEvents(event) { event ->
        if (event is OnUserLogon) {
            onLogin(event.user)
        }
    }

    ErrorModal(title = state.errorModel?.title ?: "",
        message = state.errorModel?.message ?: "",
        showError = state.errorModel != null, onDismiss = {
            viewModel.onEventHandler(LoginEvent.ClearErrorLogin)
        })


    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            item {
                Box(
                    Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(200.dp),
                        painter = painterResource(Res.drawable.loading),
                        tint = primary,
                        contentDescription = null
                    )
                }
            }
            item {
                Box(Modifier.padding(top = 16.dp)) {
                    EditInputField(
                        query = state.email,
                        errorText = state.emailError,
                        error = state.isEmailError,
                        labelText = stringResource(Res.string.email),
                        onQueryChange = {
                            viewModel.onEventHandler(LoginEvent.OnEmailChanged(it))
                        },
                        modifier = Modifier
                    )
                }
            }
            item {
                Box(Modifier.padding(top = 16.dp)) {
                    EditInputField(
                        keyboardType = KeyboardType.Password,
                        query = state.password,
                        labelText = stringResource(Res.string.password),
                        error = state.isPasswordError,
                        onQueryChange = {
                            viewModel.onEventHandler(LoginEvent.OnPasswordChanged(it))
                        },
                        modifier = Modifier,
                        errorText = state.passwordError,
                    )
                }
            }
            item {
                Box(Modifier.padding(top = 24.dp).clickable {
                    viewModel.onEventHandler(LoginEvent.OnClickPasswordForgot)
                }) {
                    Text(
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        textAlign = TextAlign.End,
                        text = stringResource(Res.string.forgot_password),
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
            item {
                Box(Modifier.padding(top = 24.dp)) {
                    Text(
                        modifier = Modifier.clickable { onSignUp() },
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        textAlign = TextAlign.End,
                        text = stringResource(Res.string.create_account),
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
            item {
                Box(Modifier.padding(top = 24.dp)) {
                    ActionButton(
                        isValid = state.isValid(),
                        text = stringResource(Res.string.login),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        viewModel.onEventHandler(LoginEvent.OnLoginProcess)
                    }
                }
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
