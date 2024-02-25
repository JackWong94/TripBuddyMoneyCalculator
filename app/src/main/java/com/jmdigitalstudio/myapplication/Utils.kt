package com.jmdigitalstudio.myapplication

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