package com.jmdigitalstudio.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
                    //CalculatingManager.summary()
                    //CalculatingManager.findPayoffSolution()
                    ItemDetailsList(ItemManager.items)
                }
            }
        }
    }
}
@Composable
fun ItemDetailsList(itemList: List<Item>) {
    Column {
        LazyColumn {
            items(itemList) {
                ItemDetailsCard(item = it)
            }
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "+")
        }
    }
}

@Composable
fun ItemDetailsCard(item: Item, modifier: Modifier = Modifier) {
    Card (
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row {
            Box(
                modifier = Modifier.weight(0.1f)
            ) {
                Text(text = item.name)
            }
            Box (
                modifier = Modifier.weight(0.2f)
            ) {
                Column {
                    Text(text = "Price :" + item.price)
                    Text(text = "Paid by : " + item.paidBy.name)
                }
            }
            Box (
                modifier = Modifier.weight(1f)
            ) {
                Column {
                    Text(text = "Owed by: ")
                    HorizontalSrollingView(item = item)
                }
            }
            Box (
                modifier = Modifier.weight(0.1f)
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "+")
                }
            }
        }
    }
}

@Composable
fun HorizontalSrollingView(item: Item) {
    LazyRow {
        val oweByList = item.owedBy.zip(item.owedAmount) { person, amount ->
            "${person.name} ${amount}"
        }
        items(oweByList) { oweBy ->
            Button(onClick = { /*TODO*/ }) {
                Text(text = oweBy)
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
        // Reset ItemManager and PersonManager before adding a new item
        ItemManager.items.clear()
        PersonManager.people.clear()
        PersonManager.addPerson("Jack")
        PersonManager.addPerson("Moon")
        PersonManager.addPerson("Hui")
        PersonManager.addPerson("Anna")
        // Add a new item to ItemManager
        ItemManager.addOwingItem("Oyster", 20.0, PersonManager.getPersonByName("Jack"), PersonManager.people)

        // Use remember to ensure itemData is recomposed properly
        val itemData = remember { ItemManager.items.firstOrNull() }
        if (itemData != null) {
            ItemDetailsCard(item = itemData)
        } else {
            // Handle the case when there are no items
            Text(text = "No items available")
        }
    }
}