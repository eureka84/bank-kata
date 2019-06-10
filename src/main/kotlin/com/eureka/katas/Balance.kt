package com.eureka.katas

import java.time.LocalDateTime

sealed class Operation {
    abstract val amount: Int
    abstract val date: LocalDateTime
}

data class Deposit(override val amount: Int, override val date: LocalDateTime) : Operation()
data class Withdraw(override val amount: Int, override val date: LocalDateTime) : Operation()


data class Balance(private val clock: Clock) {

    val operations: MutableList<Operation> = mutableListOf()

    val value: Int
        get() = operations.fold(0)
        { acc, op ->
            when(op) {
                is Deposit -> acc + op.amount
                is Withdraw -> acc - op.amount
            }
        }

    fun increaseBy(amount: Int) {
        operations.add(Deposit(amount, clock.now()))
    }

    fun decreaseBy(amount: Int) {
        if(value - amount < 0 ){
            throw WithdrawDenied()
        }
        operations.add(Withdraw(amount, clock.now()))
    }
}

