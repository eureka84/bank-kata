package com.eureka.katas

class Account(
    private val balancePrinter: BalancePrinter,
    private val balance: Balance = Balance(DefaultClock())
) {

    fun deposit(amount: Int){
        balance.increaseBy(amount)
    }
    fun withdraw(amount: Int){
        balance.decreaseBy(amount)
    }
    fun printStatement(){
        balancePrinter.print(balance)
    }
}