package com.eureka.bankkata.stubs

import com.eureka.bankkata.Clock
import java.time.LocalDateTime

class FixedClock(val date: LocalDateTime) : Clock {
    override fun now(): LocalDateTime = date

}
