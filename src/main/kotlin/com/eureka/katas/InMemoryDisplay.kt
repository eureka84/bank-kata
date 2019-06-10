package com.eureka.katas

class InMemoryDisplay(): Display {

    private val lines: MutableList<String> = mutableListOf()

    override fun print(message: String) {
        lines.add(message)
    }

    fun flush(): String =
        lines.joinToString(separator = "\n", prefix = "Date || Amount || Balance")

}