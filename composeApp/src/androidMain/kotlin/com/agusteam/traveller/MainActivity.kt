package com.agusteam.traveller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.agusteam.traveller.presenter.signup.navigation.MainNavigationFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(Modifier.padding(top = 32.dp, bottom = 32.dp)) {
                MainNavigationFlow()
            }
        }
    }
}