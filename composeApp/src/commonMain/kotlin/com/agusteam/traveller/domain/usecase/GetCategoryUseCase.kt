package com.agusteam.traveller.domain.usecase

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.domain.interfaces.CategoryRepository
import com.agusteam.traveller.domain.models.CategoryModel

class GetCategoryUseCase(private val repository: CategoryRepository) {
    suspend operator fun invoke(): OperationResult<List<CategoryModel>> {
        return repository.getCategories()
    }
}