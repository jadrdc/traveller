package com.agusteam.traveller.presenter.explore.state

import com.agusteam.traveller.core.base.ViewModelState
import com.agusteam.traveller.domain.models.ErrorModel
import com.agusteam.traveller.domain.models.TripModel

data class ExploreState(
    val userId: String = "",
    val errorModel: ErrorModel? = null,
    val categoryState: CategoryState = CategoryState(),
    val items: List<TripModel> = listOf(),
    val shouldBottomModal: Boolean = false,
    val filterState: ExploreFilterState = ExploreFilterState(selectedCategoryModel = null)
) : ViewModelState
