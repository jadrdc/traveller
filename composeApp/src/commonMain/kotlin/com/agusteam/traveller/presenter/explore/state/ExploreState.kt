package com.agusteam.traveller.presenter.explore.state

import com.agusteam.traveller.core.base.ViewModelState
import com.agusteam.traveller.domain.models.CategoryModel
import com.agusteam.traveller.domain.models.ErrorModel
import com.agusteam.traveller.domain.models.TripModel
import com.agusteam.traveller.presenter.explore.state.ExploreFilterState

data class ExploreState(
    val isLoadingSkeleton: Boolean = true,
    val selectedCategoryModel: CategoryModel? = null,
    val categories: List<CategoryModel> = listOf(),
    val errorModel: ErrorModel? = null,


    val items: List<TripModel> = listOf(),
    val shouldBottomModal: Boolean = false,
    val filterState: ExploreFilterState = ExploreFilterState(selectedCategoryModel)
) : ViewModelState
