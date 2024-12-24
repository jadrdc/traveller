package com.agusteam.traveller.presenter.explore.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.agusteam.traveller.presenter.common.BottomModalSheet
import com.agusteam.traveller.presenter.common.SearchBar
import com.agusteam.traveller.presenter.common.loading.ExploreScreenShimmerEffect
import com.agusteam.traveller.presenter.explore.composable.CategorySection
import com.agusteam.traveller.presenter.explore.composable.HomeFilterContent
import com.agusteam.traveller.presenter.explore.composable.TripItem
import com.agusteam.traveller.presenter.explore.viewmodels.ExploreEvent
import com.agusteam.traveller.presenter.explore.viewmodels.ExploreViewModel
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(viewModel: ExploreViewModel = koinViewModel(), goDetails: () -> Unit) {

    val state = viewModel.state.collectAsStateWithLifecycle()
    val bottomState = rememberModalBottomSheetState(
        false,
    )
    var isLoading by remember{
        mutableStateOf(true)
    }
    LaunchedEffect(Unit) {
        delay(3000) // Delay for 3 seconds
        isLoading = false
    }
    if (isLoading) {
        ExploreScreenShimmerEffect()
    } else {
        LazyColumn(
            Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SearchBar(Modifier) { value ->
                    viewModel.onExploreEventChanged(
                        ExploreEvent.OnFilterChanged(
                            value
                        )
                    )
                }
            }
            item {
                CategorySection(
                    categories = state.value.categories,
                    onCategorySelected = { event -> viewModel.onExploreEventChanged(event) },
                )
            }
            items(state.value.items) { item ->
                TripItem(item, onClick = { goDetails() }, toggleFavorite = { event ->
                    viewModel.onExploreEventChanged(event)
                })
            }
        }
    }
    if (state.value.shouldBottomModal) {
        BottomModalSheet(
            sheetState = bottomState,
            onDismiss = {
                viewModel.onExploreEventChanged(
                    ExploreEvent.OnFilterChanged(
                        false
                    )
                )
            },
            content = {
                HomeFilterContent(
                    exploreState = state.value,
                    onEventChanged = { event -> viewModel.onExploreEventChanged(event) },
                )
            },
        )

    }
}