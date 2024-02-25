package com.jmdigitalstudio.myapplication

object Utils {
    fun Double.roundTo2Decimal(): Double {
        return String.format("%.2f", this).toDouble()
    }
}