package com.agusteam.traveller.data.imp

import com.agusteam.traveller.core.base.OperationResult
import com.agusteam.traveller.data.mappers.toDomainModel
import com.agusteam.traveller.data.network.services.CategoryService
import com.agusteam.traveller.domain.interfaces.CategoryRepository
import com.agusteam.traveller.domain.models.CategoryModel

class CategoryRepositoryImpl(private val service: CategoryService):CategoryRepository {
    override suspend fun getCategories(): OperationResult<List<CategoryModel>> {
        return try {
            when (val result = service.getCategories()) {
                is OperationResult.Success -> {
                    val model = result.data.map {
                        it.toDomainModel()
                    }
                    OperationResult.Success(model)
                }

                is OperationResult.Error -> result
            }
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}