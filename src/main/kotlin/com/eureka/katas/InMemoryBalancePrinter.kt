package com.eureka.katas

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InMemoryBalancePrinter : BalancePrinter {

    data class StatementLine(val date: LocalDateTime, val amount: String, val balance: Int)

    private var lines: MutableList<StatementLine> = mutableListOf()

    override fun print(balance: Balance) {
        balance.operations.fold(0) { acc, op ->
            when (op) {
                is Deposit -> {
                    (op.amount + acc).also { newBalance ->
                        lines.add(StatementLine(op.date, "${op.amount}", newBalance))
                    }
                }
                is Withdraw -> {
                    (acc - op.amount).also { newBalance ->
                        lines.add(StatementLine(op.date, "-${op.amount}", newBalance))
                    }
                }
            }
        }
    }

    fun printedLines(): String =
        lines.asReversed().joinToString(separator = "\\n", prefix = "Date || Amount || Balance\\n")
        { (date, amount, balance) ->
            "${date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))} || $amount || $balance"
        }

}