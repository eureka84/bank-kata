package com.eureka.katas

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import java.time.LocalDateTime

class AcceptanceTest {

    private val clock: Clock = SequentialClock(
        LocalDateTime.parse("2012-01-10T00:00:00"),
        LocalDateTime.parse("2012-01-13T00:00:00"),
        LocalDateTime.parse("2012-01-14T00:00:00")
    )


    @Test
    fun `full interaction`() {

        val display = InMemoryBalancePrinter()

        val account = Account(display, Balance(clock))

        account.deposit(1000) // on 10-01-2012
        account.deposit(2000) // on 13-01-2012
        account.withdraw(500) // on 14-01-2012

        account.printStatement()

        assertThat(
            display.printedLines(), equalTo(
                "Date || Amount || Balance\\n"+
                "14/01/2012 || -500 || 2500\\n" +
                "13/01/2012 || 2000 || 3000\\n" +
                "10/01/2012 || 1000 || 1000"
            )
        )
    }
}
