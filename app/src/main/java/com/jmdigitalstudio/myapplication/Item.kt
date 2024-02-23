package com.jmdigitalstudio.myapplication

data class Item (
    val name: String,
    val price: Double,
    val paidBy: Person,
    val owedBy: List<Person>,
    val owedAmount: List<Double> = List(owedBy.size) { price / owedBy.size }
)

object ItemManager {
    fun addItemWithEqualOwingAmount(
        name: String,
        price: Double,
        paidBy: Person,
        owedBy: List<Person>
    ): Item {
        val item = Item(name, price, paidBy, owedBy)
        return item
    }
    fun addItemWithUnequalOwingAmount(
        name: String,
        price: Double,
        paidBy: Person,
        owedBy: List<Person>,
        owedAmounts: List<Double>
    ): Item {
        val item = Item(name, price, paidBy, owedBy, owedAmounts)
        return item
    }

}