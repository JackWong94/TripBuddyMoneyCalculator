package com.jmdigitalstudio.myapplication

import android.util.Log

data class Item (
    val name: String,
    val price: Double,
    val paidBy: Person,
    val owedBy: List<Person>,
    val owedAmount: List<Double>
)

object ItemManager {

    var items = mutableListOf<Item>()
    fun addOwingItem(
        name: String,
        price: Double,
        paidBy: Person,
        owedBy: List<Person>,
        owedAmount: List<Double> = List(owedBy.size) { price / owedBy.size }
    ): Boolean {
        if (items.any {it.name.equals(name)}) {
            Log.d("JACK","ITEM ALREADY EXIST")
            return false
        } else {
            items.add(Item(name, price, paidBy, owedBy, owedAmount))
            setPaidAmountToPerson(paidBy, price)
            setOweAmountToPerson(owedBy, owedAmount)
            return true
        }
    }

    fun setOweAmountToPerson(owedBy: List<Person>, owedAmount: List<Double>) {
        require(owedBy.size == owedAmount.size) {"List sizes must match"}

        owedBy.zip(owedAmount).forEach() {(owedBy, amount) ->
            owedBy.owedTotal += amount
        }
    }

    fun setPaidAmountToPerson(paidBy: Person, amount: Double) {
        paidBy.paidTotal += amount
    }

}