package com.jmdigitalstudio.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jmdigitalstudio.myapplication.ui.theme.TripBuddyMoneyCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TripBuddyMoneyCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatingManager.calculationSetup()
                    Greeting(CalculatingManager.summary())
                    CalculatingManager.findPayoffSolution()
                }
            }
        }
    }
}

@Composable
fun Greeting(content: String, modifier: Modifier = Modifier) {
    Text(
        text = "Welcome To Trip Buddy Money Calculator\n $content",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TripBuddyMoneyCalculatorTheme {
        Greeting("Android")
    }
}