package pl.waw.sezamkowo.core.domain.child

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import pl.waw.sezamkowo.core.domain.common.Entity
import java.time.LocalDate
import java.util.*

@TypeAlias("Child")
class Child(
        val name: String,
        val surname: String,
        val parent1: Parent?,
        val parent2: Parent?,
        val dateBirth: LocalDate,
        val graduateDate: LocalDate?,
        val entryDate: LocalDate?,
        val nurseryType: NurseryType,
        @Id val id: String = UUID.randomUUID().toString()
) : Entity