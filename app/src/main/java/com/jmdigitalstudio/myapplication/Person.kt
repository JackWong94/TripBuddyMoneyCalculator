package com.jmdigitalstudio.myapplication

data class Person (
    val name: String,
    val paidTotal: Double = 0.0,
    val owedTotal: Double = 0.0,
    val owedAndPaidDifference: Double = 0.0
)