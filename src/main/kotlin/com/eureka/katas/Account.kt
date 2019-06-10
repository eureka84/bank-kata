package com.eureka.katas

class Account(
    private val display: Display,
    private val balance: Balance
) {

    fun deposit(amount: Int){
        balance.increaseBy(amount)
    }
    fun withdraw(amount: Int){
        balance.decreaseBy(amount)
    }
    fun printStatement(){
        TODO("Implement me")
    }
}