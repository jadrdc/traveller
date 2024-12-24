package com.agusteam.traveller.presenter.explore.state

import com.agusteam.traveller.domain.models.CategoryModel

data class ExploreFilterState(
    val selectedCategoryModel: CategoryModel?,
    val searchText: String = "",
    val minimumAmount: Float = 1000f,
    val maximumAmount: Float = 13000f,
    val selectedAmount: Float = 1000f
)