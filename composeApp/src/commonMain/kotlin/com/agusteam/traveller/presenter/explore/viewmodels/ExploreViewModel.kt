package com.agusteam.traveller.presenter.explore.viewmodels

import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.core.base.GenericViewModel
import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.util.POPULAR
import com.agusteam.traveller.domain.models.CategoryModel
import com.agusteam.traveller.domain.models.ErrorModel
import com.agusteam.traveller.domain.models.TripModel
import com.agusteam.traveller.domain.usecase.GetCategoryUseCase
import com.agusteam.traveller.presenter.createShoppingItems
import com.agusteam.traveller.presenter.explore.state.ExploreFilterState
import com.agusteam.traveller.presenter.explore.state.ExploreState
import kotlinx.coroutines.launch

class ExploreViewModel(val getCategoryUseCase: GetCategoryUseCase) :
    GenericViewModel<ExploreState, ExploreEvent>(ExploreState()) {
    init {
        viewModelScope.launch {
            updateState { copy(isLoadingSkeleton = true) }
            when (val result = getCategoryUseCase()) {
                is OperationResult.Error -> {
                    onErrorHappened(
                        true,
                        "¡Oops! Algo salió mal.",
                        "Intenta nuevamente más tarde o contacta nuestro soporte si el problema persiste."
                    )
                }

                is OperationResult.Success -> {
                    val categories =
                        result.data.map { it.copy(isSelected = it.description == POPULAR) }
                    updateState {
                        copy(
                            categories = categories,
                            selectedCategoryModel = categories.firstOrNull { it.isSelected },
                            filterState = ExploreFilterState(
                                selectedCategoryModel = categories.firstOrNull { it.isSelected },
                            ),
                            items = createShoppingItems(categories)
                        )
                    }
                }
            }
            updateState { copy(isLoadingSkeleton = false) }
        }
    }

    private fun updateSelectedCategory(categoryModel: CategoryModel) {
        updateState {
            copy(
                filterState = filterState.copy(selectedCategoryModel = categoryModel),
                selectedCategoryModel = categoryModel,
                categories = updateCategoriesSelection(categories, categoryModel),
            )
        }
    }

    private fun applySelectedFilter(categoryModel: CategoryModel) {
        updateState {
            copy(
                shouldBottomModal = false,
                selectedCategoryModel = categoryModel,
                categories = updateCategoriesSelection(categories, categoryModel),
                filterState = filterState.copy(searchText = ""),
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
        updateState {
            if (value) {
                copy(
                    shouldBottomModal = value
                )
            } else {
                copy(
                    filterState = filterState.copy(searchText = ""),
                    shouldBottomModal = value
                )
            }
        }
    }

    private fun clearFilter() {
        updateState {
            copy(
                filterState = filterState.copy(
                    selectedCategoryModel = selectedCategoryModel, searchText = ""
                )
            )
        }
    }

    private fun updateFilterCategory(categoryModel: CategoryModel) {
        updateState {
            copy(
                filterState = filterState.copy(selectedCategoryModel = categoryModel)
            )
        }
    }

    private fun updateSearchText(value: String) {
        updateState {
            copy(
                filterState = filterState.copy(searchText = value)
            )
        }
    }

    private fun updateSelectedAmount(value: Float) {
        updateState {
            copy(
                filterState = filterState.copy(selectedAmount = value)
            )
        }
    }


    private fun updateShoppingitem(
        item: TripModel
    ) {
        updateState {
            copy(
                items = items.map {
                    if (it === item) {
                        it.copy(isSavedForLater = !it.isSavedForLater)
                    } else {
                        it
                    }
                }
            )
        }
    }

    private fun onErrorHappened(value: Boolean, title: String = "", message: String = "") {
        val errorModel = if (!value) {
            null
        } else {
            ErrorModel(title = title, message = message)
        }
        updateState {
            copy(
                errorModel = errorModel
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

            is ExploreEvent.OnErrorModalAccepted -> {
                onErrorHappened(false)
            }
        }
    }
}

sealed interface ExploreEvent {
    data object OnErrorModalAccepted : ExploreEvent
    class OnCategorySelected(val categoryModel: CategoryModel) : ExploreEvent
    class OnShoppingItemMarked(val item: TripModel) : ExploreEvent
    class OnFilterChanged(val value: Boolean) : ExploreEvent
    data object OnFilterCleared : ExploreEvent
    data object OnFilterApplied : ExploreEvent
    data class OnSelectedFilterAmount(val selectedAmount: Float) : ExploreEvent
    data class OnFilterCategorySelected(val categoryModel: CategoryModel) : ExploreEvent
    data class OnFilterSearchChanged(val search: String) : ExploreEvent
}