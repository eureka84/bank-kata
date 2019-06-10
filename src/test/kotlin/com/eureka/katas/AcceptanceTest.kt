package com.eureka.katas

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Ignore
import org.junit.Test

class AcceptanceTest {

    @Test
    @Ignore
    fun `full interaction`() {

        val display = InMemoryDisplay()

        val account = Account(display, Balance(0))

        account.deposit(1000) // on 10-01-2012
        account.deposit(2000) // on 13-01-2012
        account.withdraw(500) // on 14-01-2012

        account.printStatement()

        assertThat(display.flush(), equalTo(
            """
                Date || Amount || Balance
                14/01/2012 || -500 || 2500
                13/01/2012 || 2000 || 3000
                10/01/2012 || 1000 || 1000
            """.trimIndent()
        ))
    }
}
