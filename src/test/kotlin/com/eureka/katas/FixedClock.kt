package com.eureka.katas

import java.time.LocalDateTime

class FixedClock(val date: LocalDateTime) : Clock {
    override fun now(): LocalDateTime = date

}
