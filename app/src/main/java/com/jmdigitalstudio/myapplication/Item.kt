package com.jmdigitalstudio.myapplication

data class Item (
    val name: String,
    val price: Double,
    val paidBy: Person,
    val owedBy: List<Person>,
    val owedAmount: List<Double>
)

object ItemManager {
    fun AddOwingItem(
        name: String,
        price: Double,
        paidBy: Person,
        owedBy: List<Person>,
        owedAmount: List<Double> = List(owedBy.size) { price / owedBy.size }
    ): Item {
        val item = Item(name, price, paidBy, owedBy, owedAmount)
        setOweAmountToPerson(owedBy, owedAmount)
        return item
    }

    fun setOweAmountToPerson(owedBy: List<Person>, owedAmount: List<Double>) {
        require(owedBy.size == owedAmount.size) {"List sizes must match"}

        owedBy.zip(owedAmount).forEach() {(owedBy, amount) ->
            owedBy.owedTotal += amount
        }
    }
}