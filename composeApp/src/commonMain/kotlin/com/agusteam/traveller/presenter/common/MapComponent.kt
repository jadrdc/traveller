package com.agusteam.traveller.presenter.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun MapComponent(modifier: Modifier,lat:Double,lng:Double)