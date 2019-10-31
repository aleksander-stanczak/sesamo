package pl.waw.sezamkowo.desktop.child

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import pl.waw.sezamkowo.core.application.child.dto.ChildDto
import pl.waw.sezamkowo.core.domain.child.Parent

class ChildTableRow(child: ChildDto) {

    val nameProperty = SimpleStringProperty(child.name)
    val dateBirthProperty = SimpleObjectProperty(child.dateBirth)
    val parent1Property = SimpleStringProperty(parentToString(child.parent1))
    val parent2Property = SimpleStringProperty(parentToString(child.parent2))

    private fun parentToString(parent: Parent?) = parent?.name + " " + parent?.phone
}