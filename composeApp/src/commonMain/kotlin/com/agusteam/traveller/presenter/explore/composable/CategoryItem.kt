package com.agusteam.traveller.presenter.explore.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agusteam.traveller.domain.models.CategoryModel
import com.agusteam.traveller.presenter.theme.CustomFontFamily
import com.agusteam.traveller.presenter.theme.grey500
import com.agusteam.traveller.presenter.theme.primary
import org.jetbrains.compose.resources.painterResource

@Composable
fun CategoryItem(
    item: CategoryModel,
    onSelected: (CategoryModel) -> Unit
) {
    val color = if (item.isSelected) primary else grey500

    Column(Modifier.clickable(interactionSource = null, indication = null) {
        onSelected(item)
    }, horizontalAlignment = Alignment.CenterHorizontally) {
        item.imageIcon?.let {
            Icon(
                modifier = Modifier.size(32.dp),
                contentDescription = null,
                tint = color,
                painter = painterResource(it),
            )
        }
        Text(
            text = item.description,
            color = color,
            modifier = Modifier.padding(top = 4.dp),
            fontWeight = FontWeight.SemiBold,
            fontFamily = CustomFontFamily(),
            fontSize = 12.sp
        )
    }
}
