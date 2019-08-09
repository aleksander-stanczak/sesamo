package pl.waw.sezamkowo.core.application.child.dto

import pl.waw.sezamkowo.core.domain.child.NurseryType
import pl.waw.sezamkowo.core.domain.child.Parent
import java.time.LocalDate

data class ChildDto(val name: String,
                    val surname: String,
                    val parent1: Parent?,
                    val parent2: Parent?,
                    val dateBirth: LocalDate,
                    val graduateDate: LocalDate?,
                    val entryDate: LocalDate?,
                    val nurseryType: NurseryType)