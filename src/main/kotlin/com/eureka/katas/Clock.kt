package com.eureka.katas

import java.time.LocalDateTime

interface Clock {
    fun now(): LocalDateTime
}

class DefaultClock: Clock {
    override fun now(): LocalDateTime = LocalDateTime.now()
}