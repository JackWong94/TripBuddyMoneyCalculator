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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
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
                    TripBuddyMoneyCalculatorApp()
                }
            }
        }
    }
}
@Composable
private fun TripBuddyMoneyCalculatorApp() {
    Column (
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        TitleDisplay()
        CurrentTripTitle()
        ItemDetailsList(ItemManager.items)
        ReadyToCalculateButton()
    }
}
@Composable
fun ReadyToCalculateButton() {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { /*TODO*/ },
        ) {
            Text(text = "END OF TRIP --> CLICK TO CALCULATE !")
        }
    }
}
@Composable
fun ItemDetailsList(itemList: List<Item>) {
    Column (
        modifier = Modifier, // Add padding to the whole Column
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        ){
            items(itemList) {
                ItemDetailsCard(item = it)
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
        FloatingActionButton(
            onClick = { /*TODO*/ },
            Modifier.size(30.dp)
        ) {
            Icon(Icons.Filled.Add, null )
        }
    }
}
@Composable
fun CurrentTripTitle() {
    CustomText(
        text = "Current Trip: Waipu Trip",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
    CustomText(text = "Click here to change to other trips", underline = true, color = Color.Blue)
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
}
@Composable
fun ItemDetailsCard(item: Item, modifier: Modifier = Modifier) {
    Card (
        modifier = modifier
            .fillMaxWidth(),
        elevation =  CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
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
                    Row {
                        CustomText(text = "Owed by: ")
                        FloatingActionButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .size(15.dp)
                        ) {
                            Icon(Icons.Filled.Add, null )
                        }
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_extra_small)))
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
            .padding(dimensionResource(R.dimen.padding_small))
            .height(30.dp),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
    ) {
        val oweByList = item.owedBy.zip(item.owedAmount) { person, amount ->
            "${person.name} ${amount}"
        }
        items(oweByList) { oweBy ->
            Button(
                onClick = { /*TODO*/ },
                shape = MaterialTheme.shapes.medium,
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 5.dp,
                    pressedElevation = 8.dp
                )
            ) {
                CustomText(text = oweBy)
            }
        }
    }
}

@Composable
fun TitleDisplay(modifier: Modifier = Modifier) {
    CustomText(
        text = "Welcome To Trip Buddy Money Calculator"
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
}

@Preview(showBackground = true)
@Composable
fun TripBuddyMoneyCalculatorPreview() {
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
        TripBuddyMoneyCalculatorApp()
    }
}

@Composable
fun CustomText(
    text: String,
    fontSize: TextUnit = 10.sp,
    color: Color = Color.Black,
    fontWeight: FontWeight? = null,
    fontStyle: FontStyle? = null,
    underline: Boolean = false, // Added underline parameter
) {
    val textDecoration = if (underline) TextDecoration.Underline else TextDecoration.None

    Text(

        text = text,
        fontSize = fontSize,
        color = color,
        fontWeight = fontWeight,
        lineHeight = 12.sp,
        fontStyle = fontStyle,
        textDecoration = textDecoration,
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
}
