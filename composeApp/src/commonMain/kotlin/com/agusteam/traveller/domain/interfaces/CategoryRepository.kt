package com.agusteam.traveller.domain.interfaces

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.models.CategoryModel

interface CategoryRepository {
    suspend fun getCategories(): OperationResult<List<CategoryModel>>

}