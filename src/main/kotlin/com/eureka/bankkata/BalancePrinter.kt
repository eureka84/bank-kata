package com.eureka.bankkata

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

interface BalancePrinter {
    fun print(balance: Balance)
}

data class StatementLine(val date: LocalDateTime, val amount: String, val balance: Int)

class InMemoryBalancePrinter : BalancePrinter {

    private var lines: MutableList<StatementLine> = mutableListOf()

    override fun print(balance: Balance) {
        balance.operations.fold(0) { acc, operation ->
            when (operation) {
                is Balance.Operation.Deposit -> {
                    (acc + operation.amount).also { newBalance ->
                        lines.add(StatementLine(operation.date, "${operation.amount}", newBalance))
                    }
                }
                is Balance.Operation.Withdraw -> {
                    (acc - operation.amount).also { newBalance ->
                        lines.add(StatementLine(operation.date, "-${operation.amount}", newBalance))
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
