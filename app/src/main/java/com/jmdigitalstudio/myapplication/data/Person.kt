package com.jmdigitalstudio.myapplication.data

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    var paidTotal: Double,
    var owedTotal: Double,
    var owedAndPaidDifferenceForCalculationPurpose: Double = 0.0,
) {
    val owedAndPaidDifference: Double
        get() = paidTotal - owedTotal
}

object PersonManager {
    var people = mutableListOf<Person>()

    fun addPerson(
        name: String,
        paidTotal: Double = 0.0,
        owedTotal: Double = 0.0,
    ): Boolean {
        if (ItemManager.items.any {it.name.equals(name)}) {
            Log.d("JACK","PERSON ALREADY EXIST")
            return false
        } else {
            people.add(Person(name = name, paidTotal = paidTotal, owedTotal = owedTotal))
            return true
        }
    }
    fun getPersonByName(name: String): Person {
        return people.find { it.name.equals(name, ignoreCase = true) }!!
    }
    fun printPersonInMostOweToMostPaidOrder() {
        val mostOweToMostPaidOrder = people.sortedBy {it.owedAndPaidDifference }
            .joinToString("\n") { "${it.name} ${it.owedAndPaidDifference}" }
        Log.d("JACK", mostOweToMostPaidOrder)
    }
    fun printPersonOwingStatus() {
        val payStatus = people
            .groupBy {it.owedAndPaidDifference > 0 }
            .map { (toBePaids, people) ->
                people.joinToString("\n") {
                    "${toBePaids} ${it.name} ${it.owedAndPaidDifference}"
                }
            }
            .joinToString("\n")

        Log.d("JACK", payStatus)
    }
}