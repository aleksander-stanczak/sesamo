package pl.waw.sezamkowo.core.domain.presence

import org.springframework.data.annotation.TypeAlias
import pl.waw.sezamkowo.core.domain.common.Entity
import java.time.LocalDate
import java.time.LocalTime

@TypeAlias("Presence")
class Presence(
        private val key: Key,
        private var _arrival: LocalTime?,
        private var _departure: LocalTime?,
        private val _meals: Set<Meal>,
        val extraActivities: MutableCollection<ExtraActivity> = mutableListOf()
) : Entity {
    companion object {
        fun createPresenceFromTime(childId: String, date: LocalDate, arrival: LocalTime, departure: LocalTime): Presence {
            val meals = calculateMeals(arrival, departure)
            val key = Key(date, childId)
            return Presence(key, arrival, departure, meals)
        }

        private fun calculateMeals(arrival: LocalTime, departure: LocalTime): Set<Meal> {

            val breakfastTime = LocalTime.parse("09:00")
            val lunch1Time = LocalTime.parse("11:30")
            val lunch2Time = LocalTime.parse("14:30")
            val supperTime = LocalTime.parse("16:15")

            val meals = mutableSetOf<Meal>()

            if (isPresentDuring(arrival, departure, breakfastTime)) {
                meals.add(Meal.BREAKFAST)
            }
            if (isPresentDuring(arrival, departure, lunch1Time) || isPresentDuring(lunch2Time, departure, lunch2Time)) {
                meals.add(Meal.LUNCH)
            }
            if (isPresentDuring(arrival, departure, supperTime)) {
                meals.add(Meal.SUPPER)
            }
            return meals
        }

        private fun isPresentDuring(arrival: LocalTime, departure: LocalTime, meal: LocalTime) = meal in arrival..departure
    }

    val arrival
        get() = _arrival

    val departure
        get() = _departure

    val meals
        get() = _meals.toSet()

    fun changePresenceTime(arrival: LocalTime, departure: LocalTime) {
        _arrival = arrival
        _departure = departure
    }

    data class Key(val date: LocalDate, val childId: String)
}