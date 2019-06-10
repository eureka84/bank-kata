package com.eureka.katas

import java.time.LocalDateTime

sealed class Operation{
    abstract val amount: Int
    abstract val date: LocalDateTime
}
data class Deposit(override val amount: Int, override val date: LocalDateTime): Operation()
data class Withdraw(override val amount: Int, override val date: LocalDateTime) : Operation()


data class Balance(val initialValue: Int = 0){

    val value: Int get() = operations.foldRight(initialValue){op, acc -> when(op) {
        is Deposit -> acc + op.amount
        is Withdraw -> acc - op.amount
    }}

    private val operations: MutableList<Operation> = mutableListOf()

    fun increaseBy(amount: Int) {
        operations.add(Deposit(amount, LocalDateTime.now()))
    }

    fun decreaseBy(amount: Int) {
        operations.add(Withdraw(amount, LocalDateTime.now()))
    }
}

