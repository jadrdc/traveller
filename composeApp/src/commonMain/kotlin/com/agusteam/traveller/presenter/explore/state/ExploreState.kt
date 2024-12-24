package com.agusteam.traveller.presenter.explore.state

import com.agusteam.traveller.domain.models.CategoryModel
import com.agusteam.traveller.domain.models.TripModel
import com.agusteam.traveller.presenter.explore.state.ExploreFilterState

data class ExploreState(
    val selectedCategoryModel: CategoryModel? = null,
    val categories: List<CategoryModel> = listOf(),
    val items: List<TripModel> = listOf(),
    val shouldBottomModal: Boolean = false,
    val filterState: ExploreFilterState = ExploreFilterState(selectedCategoryModel)
)
