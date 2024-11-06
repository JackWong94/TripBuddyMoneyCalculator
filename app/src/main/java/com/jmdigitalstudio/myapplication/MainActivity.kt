@file:Suppress("FunctionName")

package com.jmdigitalstudio.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jmdigitalstudio.myapplication.ui.theme.TripBuddyMoneyCalculatorTheme
import com.jmdigitalstudio.myapplication.ui.TripBuddyMoneyCalculatorApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TripBuddyMoneyCalculatorTheme {
                TripBuddyMoneyCalculatorApp()
            }
        }
    }
}