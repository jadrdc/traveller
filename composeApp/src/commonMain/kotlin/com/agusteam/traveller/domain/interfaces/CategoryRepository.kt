package com.agusteam.traveller.domain.interfaces

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.models.CategoryModel
import com.agusteam.traveller.domain.models.LoginModel

interface CategoryRepository {
    suspend fun getCategories(): OperationResult<List<CategoryModel>>

}