package com.agusteam.traveller.presenter.explore.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.domain.models.CategoryModel
import com.agusteam.traveller.domain.models.TripModel
import com.agusteam.traveller.presenter.createCategories
import com.agusteam.traveller.presenter.createShoppingItems
import com.agusteam.traveller.presenter.explore.state.ExploreFilterState
import com.agusteam.traveller.presenter.explore.state.ExploreState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExploreViewModel : ViewModel() {
    private var _state: MutableStateFlow<ExploreState> = MutableStateFlow(ExploreState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val categories = createCategories()
            val shoppingItems = createShoppingItems(categories)
            _state.value = _state.value.copy(
                filterState = ExploreFilterState(
                    selectedCategoryModel = categories.first(),
                ),
                selectedCategoryModel = categories.first(),
                categories = categories,
                items = shoppingItems
            )
        }
    }

    private fun updateSelectedCategory(categoryModel: CategoryModel) {
        updateState { currentState ->
            currentState.copy(
                filterState = currentState.filterState.copy(selectedCategoryModel = categoryModel),
                selectedCategoryModel = categoryModel,
                categories = updateCategoriesSelection(currentState.categories, categoryModel),
            )
        }
    }

    private fun applySelectedFilter(categoryModel: CategoryModel) {
        updateState { currentState ->
            currentState.copy(
                shouldBottomModal = false,
                selectedCategoryModel = categoryModel,
                categories = updateCategoriesSelection(currentState.categories, categoryModel),
                filterState = currentState.filterState.copy(searchText = ""),
            )
        }
    }


    private fun updateCategoriesSelection(
        categories: List<CategoryModel>, selectedModel: CategoryModel
    ): List<CategoryModel> {
        return categories.map { category ->
            category.copy(isSelected = category == selectedModel)
        }
    }


    private fun toggleFilterModal(value: Boolean) {
        updateState { currentState ->
            if (value) {
                currentState.copy(
                    shouldBottomModal = value
                )
            } else {
                currentState.copy(
                    filterState = currentState.filterState.copy(searchText = ""),
                    shouldBottomModal = value
                )
            }
        }
    }

    private fun clearFilter() {
        updateState { currentState ->
            currentState.copy(
                filterState = currentState.filterState.copy(
                    selectedCategoryModel = currentState.selectedCategoryModel, searchText = ""
                )
            )
        }
    }

    private fun updateFilterCategory(categoryModel: CategoryModel) {
        updateState { currentState ->
            currentState.copy(
                filterState = currentState.filterState.copy(selectedCategoryModel = categoryModel)
            )
        }
    }

    private fun updateSearchText(value: String) {
        updateState { currentState ->
            currentState.copy(
                filterState = currentState.filterState.copy(searchText = value)
            )
        }
    }

    private fun updateSelectedAmount(value: Float) {
        updateState { currentState ->
            currentState.copy(
                filterState = currentState.filterState.copy(selectedAmount = value)
            )
        }
    }


    private fun updateState(updateBlock: (ExploreState) -> ExploreState) {
        viewModelScope.launch {
            _state.update(updateBlock)
        }
    }

    private fun updateShoppingitem(
        item: TripModel
    ) {
        updateState { currentState ->
            currentState.copy(
                items = currentState.items.map {
                    if (it === item) {
                        it.copy(isSavedForLater = !it.isSavedForLater)
                    } else {
                        it
                    }
                }
            )
        }
    }

    fun onExploreEventChanged(event: ExploreEvent) {
        when (event) {
            is ExploreEvent.OnCategorySelected -> {
                updateSelectedCategory(event.categoryModel)
            }

            is ExploreEvent.OnFilterChanged -> {
                toggleFilterModal(event.value)
            }

            is ExploreEvent.OnFilterCleared -> {
                clearFilter()
            }

            is ExploreEvent.OnFilterApplied -> {
                state.value.filterState.selectedCategoryModel?.let { applySelectedFilter(it) }
            }

            is ExploreEvent.OnFilterCategorySelected -> {
                updateFilterCategory(event.categoryModel)
            }

            is ExploreEvent.OnFilterSearchChanged -> {
                updateSearchText(event.search)
            }

            is ExploreEvent.OnSelectedFilterAmount -> {
                updateSelectedAmount(event.selectedAmount)
            }

            is ExploreEvent.OnShoppingItemMarked -> {
                updateShoppingitem(event.item)
            }
        }
    }
}

sealed interface ExploreEvent {
    class OnCategorySelected(val categoryModel: CategoryModel) : ExploreEvent
    class OnShoppingItemMarked(val item: TripModel) : ExploreEvent
    class OnFilterChanged(val value: Boolean) : ExploreEvent
    data object OnFilterCleared : ExploreEvent
    data object OnFilterApplied : ExploreEvent
    data class OnSelectedFilterAmount(val selectedAmount: Float) : ExploreEvent
    data class OnFilterCategorySelected(val categoryModel: CategoryModel) : ExploreEvent
    data class OnFilterSearchChanged(val search: String) : ExploreEvent
}