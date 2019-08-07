package com.eureka.bankkata

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class WithdrawFromAccountTest {

    @Rule
    @JvmField
    var expectException: ExpectedException = ExpectedException.none()

    @Test
    fun `withdraw on zero balance`() {
        val balance = Balance(DefaultClock())
        val account = Account(InMemoryBalancePrinter(), balance)

        expectException.expect(Balance.WithdrawDenied::class.java)

        account.withdraw(4)
    }

    @Test
    fun `withdraw of unavailable amount`() {
        val balance = Balance(DefaultClock())
        val account = Account(InMemoryBalancePrinter(), balance)

        expectException.expect(Balance.WithdrawDenied::class.java)

        account.deposit(2)
        account.withdraw(4)
    }

    @Test
    fun `withdraw of available amount`() {
        val balance = Balance(DefaultClock())
        val account = Account(InMemoryBalancePrinter(), balance)

        account.deposit(6)
        account.withdraw(4)

        assertThat(balance.value, equalTo(2))
    }
}