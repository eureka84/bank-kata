package com.eureka.bankkata

import com.eureka.bankkata.stubs.SequentialClock
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

        val balancePrinter = InMemoryBalancePrinter()

        val account = Account(balancePrinter, Balance(clock))

        account.deposit(1000) // on 10-01-2012
        account.deposit(2000) // on 13-01-2012
        account.withdraw(500) // on 14-01-2012

        account.printStatement()

        assertThat(
            balancePrinter.printedLines(), equalTo(
                "Date || Amount || Balance\\n"+
                "14/01/2012 || -500 || 2500\\n" +
                "13/01/2012 || 2000 || 3000\\n" +
                "10/01/2012 || 1000 || 1000"
            )
        )
    }
}
