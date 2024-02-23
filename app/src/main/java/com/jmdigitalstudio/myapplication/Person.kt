package com.jmdigitalstudio.myapplication

data class Person (
    val name: String,
    var paidTotal: Double = 0.0,
    var owedTotal: Double = 0.0,
    var owedAndPaidDifference: Double = 0.0
)