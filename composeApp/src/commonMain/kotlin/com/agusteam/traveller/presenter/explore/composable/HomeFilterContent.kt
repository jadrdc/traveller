package com.agusteam.traveller.presenter.explore.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.agusteam.traveller.presenter.common.ActionButton
import com.agusteam.traveller.presenter.common.AmountSliderPicker
import com.agusteam.traveller.presenter.common.LinkButton
import com.agusteam.traveller.presenter.common.SearchField
import com.agusteam.traveller.presenter.explore.state.ExploreState
import com.agusteam.traveller.presenter.explore.viewmodels.ExploreEvent
import com.agusteam.traveller.presenter.theme.primary
import com.agusteam.traveller.presenter.theme.secondary
import org.jetbrains.compose.resources.stringResource
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.clear
import traveller.composeapp.generated.resources.filter
import traveller.composeapp.generated.resources.trip_categories

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeFilterContent(
    exploreState: ExploreState,
    onEventChanged: (ExploreEvent) -> Unit,
) {
    LazyColumn(
        Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        item {
            Box(modifier = Modifier.padding(top = 16.dp)) {
                SearchField(
                    query = exploreState.filterState.searchText,
                    onQueryChange = {
                        onEventChanged(ExploreEvent.OnFilterSearchChanged(it))
                    },

                    )
            }
        }
        item {
            AmountSliderPicker(filterState = exploreState.filterState) {
                onEventChanged(ExploreEvent.OnSelectedFilterAmount(it))
            }
        }
        item {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                color = secondary,
                text = stringResource(Res.string.trip_categories),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            FlowRow(
                Modifier.padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp), // Horizontal spacing between chips
            ) {
                exploreState.categoryState.categories.forEach { category ->
                    FilterChip(
                        selected = category === exploreState.filterState.selectedCategoryModel,
                        onClick = {
                            onEventChanged(
                                ExploreEvent.OnFilterCategorySelected(category)
                            )
                        }, label = { Text(text = category.description) }, leadingIcon = {
                            AsyncImage(
                                model = category.imageUrl,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.size(32.dp),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(primary)
                            )
                        }
                    )
                }
            }
        }
        item {
            Row(
                Modifier.fillMaxWidth().padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                LinkButton(
                    text = stringResource(Res.string.clear),
                ) {
                    onEventChanged(ExploreEvent.OnFilterCleared)
                }

                ActionButton(
                    text = stringResource(Res.string.filter),
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    onEventChanged(ExploreEvent.OnFilterApplied)
                }
            }
        }
    }
}