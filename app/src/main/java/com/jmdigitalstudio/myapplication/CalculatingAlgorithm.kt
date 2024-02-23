package com.jmdigitalstudio.myapplication

import android.util.Log

class CalculatingAlgorithm {
    /*
    Code plan: create a list of items following the spreadsheet
    1. List all person that has -ve and +ve owed and paid difference
    2. For all +ve owe person, add each -ve payment until it become 0 owing
    3. Find all possible outcome for the event and list out the last person with the largest overflow value
    4. Overflow the value to the next +ve owe person
    5. Repeat the steps
     */
    companion object {
        private var people = listOf(
            Person("Jack" ),
            Person("Moon" ),
            Person("Daniel" ),
            Person("Wang En" ),
            Person("Kan" ),
            Person("Hui" ),
            Person("Anna" ),
            Person("Mei" ),
        )

        private var items = listOf(
            ItemManager.addItemWithUnequalOwingAmount(
                "oyster",
                20.0,
                people.find { it.name.equals("Jack", ignoreCase = true) }!!,
                listOf (
                    people.find { it.name.equals("Jack", ignoreCase = true) }!!,
                    people.find { it.name.equals("Moon", ignoreCase = true) }!!,
                    people.find { it.name.equals("Daniel", ignoreCase = true) }!!,
                    people.find { it.name.equals("Wang En", ignoreCase = true) }!!,
                    people.find { it.name.equals("Kan", ignoreCase = true) }!!,
                    people.find { it.name.equals("Hui", ignoreCase = true) }!!,
                    people.find { it.name.equals("Anna", ignoreCase = true) }!!,
                    people.find { it.name.equals("Mei", ignoreCase = true) }!!,
                ),
                listOf(5.0, 2.5, 5.0, 0.0, 2.5, 2.5, 2.5, 0.0)
            )
        )
        fun result(): String {
            val itemOweDetails = items.map { item ->
                val owedDetails = item.owedBy.zip(item.owedAmount)
                    .joinToString(separator = "\n") { (person, amount) ->
                    "${person.name} owes $amount"
                }
                "${item.name}:\n$owedDetails"
            }
            Log.d("JACK", itemOweDetails.joinToString())
            return "Hello"
        }
    }
}
