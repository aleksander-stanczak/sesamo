package pl.waw.sezamkowo.core.domain.presence

import org.junit.Assert
import org.junit.Test
import pl.waw.sezamkowo.core.domain.presence.Meal.*
import java.time.LocalDate
import java.time.LocalTime


internal class PresenceTest {

    companion object {
        const val childId = "a.stanczak"
    }

    @Test
    fun createPresenceFromHours() {
        checkIfCorrectMeals("08:00", "16:30", setOf(BREAKFAST, LUNCH, SUPPER))
        checkIfCorrectMeals("11:00", "13:30", setOf(LUNCH))
        checkIfCorrectMeals("10:00", "20:00", setOf(LUNCH, SUPPER))
    }

    private fun checkIfCorrectMeals(entryTime: String, departureTime: String, expectedMeals: Set<Meal>) {
        val meals = Presence.createPresenceFromTime(
                childId,
                LocalDate.now(),
                LocalTime.parse(entryTime),
                LocalTime.parse(departureTime)).meals

        Assert.assertEquals(expectedMeals, meals)
    }
}