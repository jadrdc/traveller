package com.agusteam.traveller.core.base
sealed interface OperationResult<out D> {
    data class Success<D>(val data:D): OperationResult<D>
    data class Error(val exception: Exception): OperationResult<Nothing>
}