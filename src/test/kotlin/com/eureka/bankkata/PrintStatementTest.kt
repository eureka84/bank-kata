package com.eureka.bankkata

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime
import java.time.Month


class PrintStatementTest {

    private lateinit var clock: Clock

    @Before
    fun setUp() {
        clock = FixedClock(date = LocalDateTime.of(2012, Month.JANUARY, 10, 0, 0, 0))
    }

    @Test
    fun `statement of a newly created empty account`() {
        val display = InMemoryBalancePrinter()
        val account = Account(balancePrinter = display, balance = Balance(clock))

        account.printStatement()

        assertThat(display.printedLines(), equalTo("Date || Amount || Balance\\n"))
    }

    @Test
    fun `statement for an account with one deposit`() {
        val display = InMemoryBalancePrinter()
        val account = Account(display, balance = Balance(clock))

        account.deposit(1000)

        account.printStatement()

        assertThat(
            display.printedLines(), equalTo(
                "Date || Amount || Balance\\n" +
                "10/01/2012 || 1000 || 1000"
            )
        )
    }
}