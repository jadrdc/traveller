package com.agusteam.traveller.presenter.explore.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.agusteam.traveller.domain.models.CategoryModel
import com.agusteam.traveller.presenter.explore.viewmodels.ExploreEvent

@Composable
fun CategorySection(
    categories: List<CategoryModel>,
    onCategorySelected: (ExploreEvent.OnCategorySelected) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categories) { item ->
            CategoryItem(
                item = item, onSelected = { category ->
                    onCategorySelected(ExploreEvent.OnCategorySelected(category))
                })
        }
    }
}