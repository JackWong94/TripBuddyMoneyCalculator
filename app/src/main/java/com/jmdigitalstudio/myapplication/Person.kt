package com.jmdigitalstudio.myapplication

import android.util.Log

data class Person (
    val name: String,
    var paidTotal: Double,
    var owedTotal: Double,
    var owedAndPaidDifference: Double
)

object PersonManager {
    var people = mutableListOf<Person>()

    fun addPerson(
        name: String,
        paidTotal: Double = 0.0,
        owedTotal: Double = 0.0,
        owedAndPaidDifference: Double = 0.0
    ): Boolean {
        if (ItemManager.items.any {it.name.equals(name)}) {
            Log.d("JACK","PERSON ALREADY EXIST")
            return false
        } else {
            people.add(Person(name, paidTotal, owedTotal, owedAndPaidDifference))
            return true
        }
    }
    fun getPersonByName(name: String): Person {
        return people.find { it.name.equals(name, ignoreCase = true) }!!
    }
}