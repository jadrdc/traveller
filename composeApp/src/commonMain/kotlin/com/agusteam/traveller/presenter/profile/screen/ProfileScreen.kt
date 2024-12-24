package com.agusteam.traveller.presenter.profile.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.agusteam.traveller.domain.models.ProfileDataModel
import com.agusteam.traveller.presenter.profile.composable.ProfileItem
import com.agusteam.traveller.presenter.profile.viewmodels.ProfileViewModel
import com.agusteam.traveller.presenter.theme.primary
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.email
import traveller.composeapp.generated.resources.lastname
import traveller.composeapp.generated.resources.logout
import traveller.composeapp.generated.resources.name
import traveller.composeapp.generated.resources.phone

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    val profiles =
        listOf(
            ProfileDataModel(
                title = stringResource(Res.string.name),
                description = state.name,
                icon = Icons.Filled.Person
            ),
            ProfileDataModel(
                title = stringResource(Res.string.lastname),
                description = state.lastname,
                icon = Icons.Filled.AccountBox
            ),
            ProfileDataModel(
                title = stringResource(Res.string.email),
                description = state.email,
                icon = Icons.Filled.Email
            ),
            ProfileDataModel(
                title = stringResource(Res.string.phone),
                description = state.phone,
                icon = Icons.Filled.Phone
            )
        )
    Column(
        Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = state.name + " " + state.lastname,
            fontSize = 35.sp,
            fontWeight = FontWeight.SemiBold
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            items(profiles) { item ->
                ProfileItem(item)
            }
        }
        Box(Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally)) {
            Text(
                color = primary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                textAlign = TextAlign.End,
                text = stringResource(Res.string.logout),
            )
        }

    }
}