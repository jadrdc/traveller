package com.agusteam.traveller.presenter.common.loading

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun TripItemLoadingSection() {
    Column {
        TripItemLoading()
        TripItemLoading()
        TripItemLoading()
        TripItemLoading()
    }
}