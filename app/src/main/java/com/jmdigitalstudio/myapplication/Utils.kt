package com.jmdigitalstudio.myapplication

import androidx.room.TypeConverter

object Utils {
    fun Double.roundTo2Decimal(): Double {
        return String.format("%.2f", this).toDouble()
    }
}

object TestUtils {
    fun logTestCaseTitle() {
        val className = Thread.currentThread().stackTrace[2].className
        val simpleClassName = className.substring(className.lastIndexOf('.') + 1)
        val methodName = Thread.currentThread().stackTrace[2].methodName
        println("$simpleClassName --> Running test: $methodName")
    }
}

class StringListConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",").map { it.trim() }
    }

    @TypeConverter
    fun toString(value: List<String>): String {
        return value.joinToString(",")
    }
}
class DoubleListConverter {
    @TypeConverter
    fun fromString(value: String): List<Double> {
        return value.split(",").map { it.toDouble() }
    }

    @TypeConverter
    fun fromList(list: List<Double>): String {
        return list.joinToString(",")
    }
}
