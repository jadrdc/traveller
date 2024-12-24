package com.agusteam.traveller.presenter.common.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.agusteam.traveller.presenter.common.loading.CategoryIconLoading

@Composable
fun CategoryLoadingList() {

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CategoryIconLoading()
        CategoryIconLoading()
        CategoryIconLoading()
        CategoryIconLoading()
        CategoryIconLoading()
    }
}