package com.eureka.bankkata

import java.lang.RuntimeException
import java.time.LocalDateTime

data class Balance(private val clock: Clock = DefaultClock()) {

    val operations: MutableList<Operation> = mutableListOf()

    val value: Int
        get() = operations.fold(0) { acc, op ->
            when (op) {
                is Operation.Deposit -> acc + op.amount
                is Operation.Withdraw -> acc - op.amount
            }
        }

    fun increaseBy(amount: Int) {
        operations.add(Operation.Deposit(amount, clock.now()))
    }

    fun decreaseBy(amount: Int) {
        if (value - amount < 0) {
            throw WithdrawDenied()
        }
        operations.add(Operation.Withdraw(amount, clock.now()))
    }

    sealed class Operation {
        abstract val amount: Int
        abstract val date: LocalDateTime

        data class Deposit(override val amount: Int, override val date: LocalDateTime) : Operation()
        data class Withdraw(override val amount: Int, override val date: LocalDateTime) : Operation()
    }

    class WithdrawDenied : RuntimeException("You don't have enough money on your account")
}



