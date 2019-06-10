package com.eureka.katas

import java.time.LocalDateTime

class SequentialClock(private vararg val dates: LocalDateTime) : Clock {

    private var counter = 0

    override fun now(): LocalDateTime {
        return dates[counter % dates.size].also { counter ++ }
    }
}

