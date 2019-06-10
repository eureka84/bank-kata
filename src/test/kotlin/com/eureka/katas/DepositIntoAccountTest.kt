package com.eureka.katas

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Test


class DepositIntoAccountTest {

    @Test
    fun `single deposit`() {
        val balance = Balance(DefaultClock())
        val account = Account(balancePrinter = InMemoryBalancePrinter(), balance = balance)
        account.deposit(4)
        assertThat(balance.value, equalTo(4))
    }

    @Test
    fun `multiple deposit`() {
        val balance = Balance(DefaultClock())
        val account = Account(balancePrinter = InMemoryBalancePrinter(), balance = balance)
        account.deposit(4)
        account.deposit(7)
        assertThat(balance.value, equalTo(11))
    }

}