package com.jmdigitalstudio.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Column (
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)), // Add padding to the whole Column
    ){
        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        ){
            items(itemList) {
                ItemDetailsCard(item = it)
            }
        }
        Button(
            onClick = { /*TODO*/ },
            shape = MaterialTheme.shapes.medium
        ) {
            CustomText(text = "+")
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
                modifier = Modifier
                    .weight(0.2f)
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                CustomText(text = item.name)
            }
            Box (
                modifier = Modifier
                    .weight(0.2f)
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                Column {
                    CustomText(text = "Price :\n" + item.price)
                    CustomText(text = "Paid by:\n" + item.paidBy.name)
                }
            }
            Box (
                modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                Column {
                    Row (Modifier.padding(dimensionResource(R.dimen.padding_small))){
                        CustomText(text = "Owed by: ")
                        Button(
                            onClick = { /*TODO*/ },
                            shape = MaterialTheme.shapes.extraLarge,
                            modifier = Modifier
                                .size(15.dp)
                        ) {
                            CustomText(text = "+", fontSize = 10.sp)
                        }
                    }
                    HorizontalSrollingView(item = item)
                }
            }
        }
    }
}

@Composable
fun HorizontalSrollingView(item: Item) {
    LazyRow (
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
    ) {
        val oweByList = item.owedBy.zip(item.owedAmount) { person, amount ->
            "${person.name} ${amount}"
        }
        items(oweByList) { oweBy ->
            Button(
                onClick = { /*TODO*/ },
                shape = MaterialTheme.shapes.medium,
            ) {
                CustomText(text = oweBy)
            }
        }
    }
}

@Composable
fun Greeting(content: String, modifier: Modifier = Modifier) {
    CustomText(
        text = "Welcome To Trip Buddy Money Calculator\n $content",
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
            CustomText(text = "No items available")
        }
    }
}

@Composable
fun CustomText(
    text: String,
    fontSize: TextUnit = 10.sp,
    color: Color = Color.Black,
    fontWeight: FontWeight? = null
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        fontWeight = fontWeight,
        lineHeight = 12.sp
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
}
