package com.eureka.bankkata

import java.time.LocalDateTime

class FixedClock(val date: LocalDateTime) : Clock {
    override fun now(): LocalDateTime = date

}
