package com.agusteam.traveller.presenter.common


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.agusteam.traveller.presenter.theme.primary
import org.jetbrains.compose.resources.stringResource
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.search_hint

@Composable
fun SearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White, // Background color when focused
            focusedIndicatorColor = primary,
        ),
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(25.dp)), placeholder = {
            Text(
                text = stringResource(Res.string.search_hint),
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(8.dp)
    )
}