package com.agusteam.traveller.presenter.explore.viewmodels

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.core.base.GenericViewModel
import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.util.POPULAR
import com.agusteam.traveller.data.util.USER_ID
import com.agusteam.traveller.domain.models.CategoryModel
import com.agusteam.traveller.domain.models.ErrorModel
import com.agusteam.traveller.domain.models.TripModel
import com.agusteam.traveller.domain.usecase.GetCategoryUseCase
import com.agusteam.traveller.domain.usecase.GetPaginatedTripsUseCase
import com.agusteam.traveller.domain.usecase.GetProfileUseCase
import com.agusteam.traveller.domain.usecase.MarkFavoriteTripUseCase
import com.agusteam.traveller.domain.usecase.UnmarkedFavoriteTripUseCase
import com.agusteam.traveller.presenter.explore.state.ExploreFilterState
import com.agusteam.traveller.presenter.explore.state.ExploreState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch

class ExploreViewModel(
    val getProfileUseCase: GetProfileUseCase,
    val getCategoryUseCase: GetCategoryUseCase,
    val getPaginatedTripsUseCase: GetPaginatedTripsUseCase,
    val markFavoriteTripUseCase: MarkFavoriteTripUseCase,
    val unmarkedFavoriteTripUseCase: UnmarkedFavoriteTripUseCase
) :
    GenericViewModel<ExploreState, ExploreEvent>(ExploreState()) {

    init {
        initialLoad()
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            getProfileUseCase().mapLatest { preference ->
                val userIdKey = stringPreferencesKey(USER_ID)
                val userId = preference[userIdKey] ?: ""
                updateState {
                    copy(userId = userId)
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun initialLoad() {
        viewModelScope.launch {
            updateState { copy(categoryState = categoryState.copy(isLoadingSkeleton = true)) }
            when (val result = getCategoryUseCase()) {
                is OperationResult.Error -> {
                    updateState { copy(showUIError = true) }
                }

                is OperationResult.Success -> {
                    val categories =
                        result.data.map { it.copy(isSelected = it.description == POPULAR) }
                    when (val resultTrips = getPaginatedTripsUseCase()) {
                        is OperationResult.Error -> {}
                        is OperationResult.Success -> {
                            val tripList = resultTrips.data.map { trip ->
                                TripModel(
                                    cancellationPolicy = trip.cancellation_policy,
                                    id = trip.id,
                                    businessId = trip.businessModel.id,
                                    businessName = trip.businessModel.name,
                                    images = trip.images,
                                    businessImage = trip.businessModel.image,
                                    name = trip.name,
                                    description = trip.description,
                                    lat = trip.lat,
                                    lng = trip.lng,
                                    destiny = trip.destiny,
                                    month = trip.businessModel.month,
                                    categoryList = categories
                                )
                            }
                            updateState { copy(items = tripList) }
                        }
                    }
                    updateState {
                        copy(
                            categoryState = categoryState.copy(
                                selectedCategoryModel = categories.firstOrNull { it.isSelected },
                                categories = categories
                            ),
                            filterState = ExploreFilterState(
                                selectedCategoryModel = categories.firstOrNull { it.isSelected },
                            ),
                        )
                    }
                }
            }
            updateState { copy(categoryState = categoryState.copy(isLoadingSkeleton = false)) }
        }

    }

    private fun updateSelectedCategory(categoryModel: CategoryModel) {
        updateState {
            copy(
                categoryState = categoryState.copy(
                    selectedCategoryModel = categoryModel,
                    categories = updateCategoriesSelection(categoryState.categories, categoryModel)
                ),
                filterState = filterState.copy(selectedCategoryModel = categoryModel),
            )
        }
    }

    private fun applySelectedFilter(categoryModel: CategoryModel) {
        updateState {
            copy(
                shouldBottomModal = false,
                categoryState = categoryState.copy(
                    selectedCategoryModel = categoryModel,
                    categories = updateCategoriesSelection(categoryState.categories, categoryModel)
                ),
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
                    selectedCategoryModel = categoryState.selectedCategoryModel, searchText = ""
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
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            val markState = !item.isSavedForLater
            val result = if (markState) {
                markFavoriteTripUseCase(userId = state.value.userId, tripId = item.id)
            } else {
                unmarkedFavoriteTripUseCase(userId = state.value.userId, tripId = item.id)
            }
            when (result) {
                is OperationResult.Error -> {
                    onErrorHappened(
                        true,
                        "Error cambiando el estado de viaje",
                        "No se pudo completar la operacion,intente mas tarde."
                    )
                }

                is OperationResult.Success -> {
                    updateState {
                        copy(
                            items = items.map {
                                if (it === item) {
                                    it.copy(isSavedForLater = markState)
                                } else {
                                    it
                                }
                            }
                        )
                    }
                }
            }
            updateState { copy(isLoading = false) }
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