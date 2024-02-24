package com.jmdigitalstudio.myapplication

import android.util.Log

object CalculatingAlgorithm {
    /*
    Code plan: create a list of items following the spreadsheet
    1. List all person that has -ve and +ve owed and paid difference
    2. For all +ve owe person, add each -ve payment until it become 0 owing
    3. Find all possible outcome for the event and list out the last person with the largest overflow value
    4. Overflow the value to the next +ve owe person
    5. Repeat the steps
     */

    fun result(): String {
        PersonManager.addPerson("Jack")
        PersonManager.addPerson("Moon")
        PersonManager.addPerson("Daniel")
        PersonManager.addPerson("Wang En")
        PersonManager.addPerson("Kan")
        PersonManager.addPerson("Hui")
        PersonManager.addPerson("Anna")
        PersonManager.addPerson("Mei")

        ItemManager.addOwingItem("oyster",
            20.0,
            PersonManager.getPersonByName("Jack"),
            listOf(
                PersonManager.getPersonByName("Jack"),
                PersonManager.getPersonByName("Moon"),
                PersonManager.getPersonByName("Daniel"),
                PersonManager.getPersonByName("Wang En"),
                PersonManager.getPersonByName("Kan"),
                PersonManager.getPersonByName("Hui"),
                PersonManager.getPersonByName("Anna"),
                PersonManager.getPersonByName("Mei")
            ),
            listOf(
                5.0,
                2.5,
                5.0,
                0.0,
                2.5,
                2.5,
                2.5,
                0.0
            )
        )
        ItemManager.addOwingItem("Car Daniel",
            75.2,
            PersonManager.getPersonByName("Daniel"),
            listOf(
                PersonManager.getPersonByName("Jack"),
                PersonManager.getPersonByName("Moon"),
                PersonManager.getPersonByName("Daniel"),
                PersonManager.getPersonByName("Wang En"),
                PersonManager.getPersonByName("Kan"),
                PersonManager.getPersonByName("Hui"),
                PersonManager.getPersonByName("Anna"),
                PersonManager.getPersonByName("Mei")
            )
        )
        ItemManager.addOwingItem("Car Kan",
            75.2,
            PersonManager.getPersonByName("Kan"),
            listOf(
                PersonManager.getPersonByName("Jack"),
                PersonManager.getPersonByName("Moon"),
                PersonManager.getPersonByName("Daniel"),
                PersonManager.getPersonByName("Wang En"),
                PersonManager.getPersonByName("Kan"),
                PersonManager.getPersonByName("Hui"),
                PersonManager.getPersonByName("Anna"),
                PersonManager.getPersonByName("Mei")
            )
        )
        val itemOweDetails = ItemManager.items.map { item ->
            val owedDetails = item.owedBy.zip(item.owedAmount)
                .joinToString(separator = "\n") { (person, amount) ->
                    "${person.name} owes $amount"
                }
            "${item.name}: PAID BY ${item.paidBy.name}\n$owedDetails"
        }
        Log.d("JACK", itemOweDetails.joinToString("\n"))
        Log.d("JACK", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n")
        val personOweTotal = PersonManager.people.map {person ->
            "${person.name} total owing = ${person.owedTotal}"
        }
        Log.d("JACK", personOweTotal.joinToString("\n"))
        return itemOweDetails.joinToString(separator = "\n") + personOweTotal.joinToString(separator = "\n")
    }
}
