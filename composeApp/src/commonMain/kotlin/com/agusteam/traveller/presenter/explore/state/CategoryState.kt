package com.agusteam.traveller.presenter.explore.state

import com.agusteam.traveller.core.base.ViewModelState
import com.agusteam.traveller.domain.models.CategoryModel

data class CategoryState(
    val selectedCategoryModel: CategoryModel? = null,
    val isLoadingSkeleton: Boolean = true,
    val categories: List<CategoryModel> = listOf(),
) : ViewModelState
