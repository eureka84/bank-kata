package com.eureka.bankkata

import java.lang.RuntimeException

class WithdrawDenied : RuntimeException("You don't have enough money on your account")
