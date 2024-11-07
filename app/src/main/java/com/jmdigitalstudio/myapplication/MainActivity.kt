@file:Suppress("FunctionName")

package com.jmdigitalstudio.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jmdigitalstudio.myapplication.ui.AppViewModel
import com.jmdigitalstudio.myapplication.ui.theme.TripBuddyMoneyCalculatorTheme
import com.jmdigitalstudio.myapplication.ui.TripBuddyMoneyCalculatorApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TripBuddyMoneyCalculatorTheme {
                TripBuddyMoneyCalculatorApp(appViewModel = viewModel(factory = AppViewModel.factory))
            }
        }
    }
}