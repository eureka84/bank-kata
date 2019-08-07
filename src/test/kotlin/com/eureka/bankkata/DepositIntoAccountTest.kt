package com.eureka.bankkata

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Test


class DepositIntoAccountTest {

    @Test
    fun `single deposit`() {
        val account = Account(InMemoryBalancePrinter(), Balance())
        account.deposit(4)
        assertThat(Balance().value, equalTo(4))
    }

    @Test
    fun `multiple deposit`() {
        val account = Account(InMemoryBalancePrinter(), Balance())
        account.deposit(4)
        account.deposit(7)
        assertThat(Balance().value, equalTo(11))
    }

}