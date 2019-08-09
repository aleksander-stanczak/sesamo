package pl.waw.sezamkowo.core.application.child

import org.springframework.stereotype.Service
import pl.waw.sezamkowo.core.application.child.dto.ChildDto
import pl.waw.sezamkowo.core.domain.child.Child
import pl.waw.sezamkowo.core.domain.child.ChildRepository

@Service
class ChildManagementService(private val childRepository: ChildRepository) {
    fun createChild(dto: ChildDto) {
        val child = with(dto) {
            Child(
                    name,
                    surname,
                    parent1,
                    parent2,
                    dateBirth,
                    graduateDate,
                    entryDate,
                    nurseryType)
        }
        childRepository.save(child)
    }

    fun updateChild(childId: String, dto: ChildDto) {
        val child = with(dto) {
            Child(
                    name,
                    surname,
                    parent1,
                    parent2,
                    dateBirth,
                    graduateDate,
                    entryDate,
                    nurseryType,
                    childId)
        }
        childRepository.save(child)
    }

    fun deleteChild(childId: String) {
        childRepository.deleteById(childId)
    }

    fun listChildren(): List<ChildDto> {
        return childRepository.findAll().map(::toDto)
    }

    private fun toDto(child: Child) = with(child) { ChildDto(name, surname, parent1, parent2, dateBirth, graduateDate, entryDate, nurseryType) }
}