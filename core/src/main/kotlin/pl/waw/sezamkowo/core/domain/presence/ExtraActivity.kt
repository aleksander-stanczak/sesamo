package pl.waw.sezamkowo.core.domain.presence

import java.time.YearMonth

abstract class ExtraActivity(val name: String, val code: String) {
    override fun toString() = code
    abstract fun calculatePrice(date: YearMonth): Double
}

class Logopedy : ExtraActivity("Logopedia", "L") {
    override fun calculatePrice(date: YearMonth): Double {
        val firstRise = YearMonth.of(2018, 3)
        return when {
            date.isBefore(firstRise) -> 30.0
            else -> 35.0
        }
    }
}