package com.agusteam.traveller.presenter.explore.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.agusteam.traveller.domain.models.TripModel
import com.agusteam.traveller.presenter.common.BottomModalSheet
import com.agusteam.traveller.presenter.common.ErrorModal
import com.agusteam.traveller.presenter.common.SearchBar
import com.agusteam.traveller.presenter.common.loading.TripItemLoadingSection
import com.agusteam.traveller.presenter.explore.composable.CategorySection
import com.agusteam.traveller.presenter.explore.composable.HomeFilterContent
import com.agusteam.traveller.presenter.explore.composable.TripItem
import com.agusteam.traveller.presenter.explore.viewmodels.ExploreEvent
import com.agusteam.traveller.presenter.explore.viewmodels.ExploreViewModel
import com.agusteam.traveller.presenter.theme.primary
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel = koinViewModel(),
    goDetails: (TripModel, String) -> Unit
) {

    val state = viewModel.state.collectAsStateWithLifecycle()
    val bottomState = rememberModalBottomSheetState(
        false,
    )

    ErrorModal(title = state.value.errorModel?.title ?: "",
        message = state.value.errorModel?.message ?: "",
        showError = state.value.errorModel != null, onDismiss = {
            viewModel.onExploreEventChanged(ExploreEvent.OnErrorModalAccepted)
        })
    Box() {
        LazyColumn(
            Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SearchBar(
                    Modifier,
                    isloadingn = state.value.categoryState.isLoadingSkeleton
                ) { value ->
                    viewModel.onExploreEventChanged(
                        ExploreEvent.OnFilterChanged(
                            value
                        )
                    )
                }
            }
            item {
                CategorySection(
                    categoryState = state.value.categoryState,
                    onCategorySelected = { event -> viewModel.onExploreEventChanged(event) },
                )
            }
            if (state.value.categoryState.isLoadingSkeleton) {
                item {
                    TripItemLoadingSection()
                }
            } else {
                items(state.value.items) { item ->
                    TripItem(
                        item,
                        onClick = { goDetails(item, state.value.userId) },
                        toggleFavorite = { event ->
                            viewModel.onExploreEventChanged(event)
                        })
                }
            }
        }
        if (state.value.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(enabled = false) {}, // Prevents interaction
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = primary)
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
}