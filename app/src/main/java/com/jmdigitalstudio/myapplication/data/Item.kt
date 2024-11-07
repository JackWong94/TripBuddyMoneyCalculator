package com.jmdigitalstudio.myapplication.data

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jmdigitalstudio.myapplication.DoubleListConverter

@Entity(tableName = "items")
data class Item (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    @TypeConverters(PersonConverter::class)
    val paidBy: Person,
    @TypeConverters(DoubleListConverter::class)
    val owedAmount: List<Double>,
    @TypeConverters(PersonListConverter::class)
    val owedBy: List<Person>
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
            items.add(Item(name = name, price = price, paidBy = paidBy, owedBy = owedBy, owedAmount = owedAmount))
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
class ItemConverter {
    @TypeConverter
    fun fromString(value: String): Item {
        return Gson().fromJson(value, Item::class.java)
    }

    @TypeConverter
    fun fromItem(item: Item): String {
        return Gson().toJson(item)
    }
}

class ItemListConverter {
    @TypeConverter
    fun fromString(value: String): List<Item> {
        val type = object : TypeToken<List<Item>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<Item>): String {
        return Gson().toJson(list)
    }
}