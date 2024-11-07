package com.jmdigitalstudio.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jmdigitalstudio.myapplication.CalculatingManager
import com.jmdigitalstudio.myapplication.R
import com.jmdigitalstudio.myapplication.data.Item
import com.jmdigitalstudio.myapplication.data.ItemManager
import com.jmdigitalstudio.myapplication.data.PersonManager
import com.jmdigitalstudio.myapplication.data.Trip
import com.jmdigitalstudio.myapplication.ui.theme.TripBuddyMoneyCalculatorTheme

@Composable
fun TripBuddyMoneyCalculatorApp(appViewModel: AppViewModel = viewModel(factory = AppViewModel.factory)) {
    val trip by appViewModel.trips.collectAsState(initial = emptyList())
    TripBuddyMoneyCalculatorApp(trip)
}
@Composable
fun TripBuddyMoneyCalculatorApp(trip: List<Trip>) {
    var showAddTripDialog by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        TitleDisplay()
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
        if (showAddTripDialog) {
            AddItemsAndPeopleDialog(onDismiss = { showAddTripDialog = false })
        }
        LazyColumn (modifier = Modifier.fillMaxWidth()){
            items(trip) { tripItem -> // Use 'items' instead of 'forEach'
                Button(
                    onClick = { /* Handle click for this trip */ },
                    modifier = Modifier
                        .fillMaxWidth()   // Make button full width or set custom width
                        .padding(8.dp)    // Padding around each button
                ) {
                    Text(text = tripItem.name) // Assuming each trip has a 'name' property
                }
            }
        }
        FloatingActionButton(
            onClick = {showAddTripDialog = true},
            Modifier.size(30.dp)
        ) {
            Icon(Icons.Filled.Add, null )
        }
    }
}

@Composable
fun CurrentTripScreen(appViewModel: AppViewModel = viewModel(factory = AppViewModel.factory)) {
    val items by appViewModel.items.collectAsState(initial = emptyList())
    CurrentTripScreen(items, onClickAddItem = { /* TO TO*/ })
}

@Composable
fun CurrentTripScreen(items: List<Item>, onClickAddItem: () -> Unit) {

    Column (
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Box(modifier = Modifier.weight(1f)) {
            CurrentTripTitle()
        }
        Box(modifier = Modifier.weight(5f)) {
            ItemDetailsList(items, onClickAddItem )
        }
        Box(modifier = Modifier.weight(1f)) {
            ReadyToCalculateButton()
        }
    }
}

@Composable
fun TitleDisplay() {
    Text(
        text = "Welcome To Trip Buddy\n Money Calculator",
        style = MaterialTheme.typography.displayLarge,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
}
@Composable
fun CurrentTripTitle() {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = "Current Trip: Waipu Trip",
            style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
        Text(
            text = "Click here to change to other trips",
            textDecoration = TextDecoration.Underline,
            color = Color.Blue,
            modifier = Modifier.clickable { /* Handle click event here */ },
        )
    }
}
@Composable
fun ItemDetailsList(itemList: List<Item>, onClickAddItem: () -> Unit) {
    var showAddItemAndOwingPeopleDialog by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier, // Add padding to the whole Column
        verticalArrangement = Arrangement.Top,
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
            onClick = { showAddItemAndOwingPeopleDialog = true },
            Modifier.size(30.dp)
        ) {
            Icon(Icons.Filled.Add, null )
        }
        if (showAddItemAndOwingPeopleDialog) {
            AddItemsAndPeopleDialog(onDismiss = { showAddItemAndOwingPeopleDialog = false })
        }
    }
}
@Composable
fun ItemDetailsCard(item: Item, modifier: Modifier = Modifier) {
    Card (
        modifier = modifier
            .fillMaxWidth(),
        elevation =  CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp, topEnd = 10.dp, bottomEnd = 10.dp)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .weight(0.2f)
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.displaySmall,
                )
            }
            Box (
                modifier = Modifier
                    .weight(0.2f)
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                Column {
                    Text(text = "Price :",style = MaterialTheme.typography.displaySmall)
                    Text(text = "${item.price}")
                    Text(text = "Paid by:",style = MaterialTheme.typography.displaySmall)
                    Text(text = item.paidBy.name)
                }
            }
            Box (
                modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                Column {
                    Row {
                        Text(text = "Owed by: ", style = MaterialTheme.typography.displaySmall)
                        FloatingActionButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .size(15.dp)
                        ) {
                            Icon(Icons.Filled.Add, null )
                        }
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_extra_small)))
                    HorizontalScrollingView(item = item)
                }
            }
        }
    }
}
@Composable
fun HorizontalScrollingView(item: Item) {
    LazyRow (
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(dimensionResource(R.dimen.padding_small))
            .height(30.dp),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
    ) {
        val oweByList = item.owedBy.zip(item.owedAmount) { person, amount ->
            "${person.name} $amount"
        }
        items(oweByList) { oweBy ->
            FilledTonalButton (
                onClick = { /*TODO*/ },
                shape = MaterialTheme.shapes.small,
            ) {
                Text(
                    text = oweBy,
                    style = MaterialTheme.typography.displaySmall,
                )
            }
        }
    }
}
@Composable
fun ReadyToCalculateButton() {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { showDialog = true },
        ) {
            Text(
                text = "END OF TRIP --> CLICK TO CALCULATE !",
                style = MaterialTheme.typography.displayMedium
            )
        }
        if (showDialog) {
            CalculationResultDialog(onDismiss = { showDialog = false })
        }
    }
}
@Composable
fun CalculationResultDialog(onDismiss: ()-> Unit ) {
    CustomDialog(
        content = {
            // Your composable content here
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Pay Back Plan Calculated",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center // Align text to center horizontally
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
                Box (
                    Modifier
                        .border(1.dp, Color.Black)
                        .shadow(2.dp)
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                        .verticalScroll(rememberScrollState())
                        .fillMaxWidth()
                ) {
                    Text(text = CalculatingManager.findPayoffSolution())
                }
            }
        },
        onDismiss = onDismiss,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemsAndPeopleDialog(onDismiss: ()-> Unit ) {
    var item by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var people = PersonManager.people
    CustomDialog(
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    "Add Items & Owing People",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center // Align text to center horizontally
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
                TextField(
                    value = item,
                    onValueChange = { item = it },
                    label = { Text("Item name",fontSize = 15.sp) },
                    placeholder = { Text("Enter item name",fontSize = 15.sp) }
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
                TextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Item price",fontSize = 15.sp) },
                    placeholder = { Text("Enter item price",fontSize = 15.sp) }
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
                Text(
                    "Paid By",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center // Align text to center horizontally
                )
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    people.forEach { person ->
                        Row {
                            Button(
                                onClick = { /* Handle button click for this person */ },
                                modifier = Modifier.size(100.dp, 40.dp)
                            ) {
                                Text(text = person.name) // Display person's name on the button
                            }
                        }
                    }
                }
                Text(
                    "Owe By",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center // Align text to center horizontally
                )
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    people.forEach { person ->
                        Row {
                            Button(
                                onClick = { /* Handle button click for this person */ },
                                modifier = Modifier.size(100.dp, 40.dp)
                            ) {
                                Text(text = person.name) // Display person's name on the button
                            }
                        }
                    }
                }
            }
        },
        onDismiss = onDismiss,
    )
}

@Composable
fun CustomDialog(content: @Composable () -> Unit, onDismiss: ()-> Unit) {
    Dialog (
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp, 20.dp, 5.dp, 10.dp),
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 8.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    content()
                }
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)
                ) {
                    Text(
                        "Close",
                        style = MaterialTheme.typography.displayMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TripBuddyMoneyCalculatorAppPreview() {
    TripBuddyMoneyCalculatorTheme {
        TripBuddyMoneyCalculatorApp(emptyList<Trip>())
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentTripScreenPreview() {
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

        CurrentTripScreen(ItemManager.items, onClickAddItem = {})
    }
}

@Preview(showBackground = true)
@Composable
fun CalculationResultDialogPreview() {
    TripBuddyMoneyCalculatorTheme {
        CalculationResultDialog(onDismiss = {})
    }
}

@Preview(showBackground = true)
@Composable
fun AddItemsAndPeopleDialogPreview() {
    TripBuddyMoneyCalculatorTheme {
        AddItemsAndPeopleDialog(onDismiss = {})
    }
}


