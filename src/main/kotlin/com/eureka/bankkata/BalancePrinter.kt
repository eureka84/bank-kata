package com.eureka.bankkata

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

interface BalancePrinter {
    fun print(balance: Balance)
}

class InMemoryBalancePrinter : BalancePrinter {

    data class StatementLine(val date: LocalDateTime, val amount: String, val balance: Int)

    private var lines: MutableList<StatementLine> = mutableListOf()

    override fun print(balance: Balance) {
        balance.operations.fold(0) { acc, op ->
            when (op) {
                is Balance.Operation.Deposit -> {
                    (acc + op.amount).also { newBalance ->
                        lines.add(StatementLine(op.date, "${op.amount}", newBalance))
                    }
                }
                is Balance.Operation.Withdraw -> {
                    (acc - op.amount).also { newBalance ->
                        lines.add(StatementLine(op.date, "-${op.amount}", newBalance))
                    }
                }
            }
        }
        lines = lines.asReversed()
    }

    fun printedLines(): String =
        DateTimeFormatter.ofPattern(DATE_PATTERN).let { dateTimeFormatter ->
            lines.joinToString(separator = "\\n", prefix = HEADER) { (date, amount, balance) ->
                "${date.format(dateTimeFormatter)} || $amount || $balance"
            }
        }

}

const val DATE_PATTERN = "dd/MM/yyyy"
const val HEADER = "Date || Amount || Balance\\n"
