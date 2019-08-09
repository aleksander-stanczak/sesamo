package pl.waw.sezamkowo.desktop.child

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import pl.waw.sezamkowo.core.application.child.ChildManagementService
import pl.waw.sezamkowo.desktop.common.ViewModel

@ViewModel
class ChildViewModel(private val childManagementService: ChildManagementService) {
    val items: ObservableList<ChildTableRow> = FXCollections.observableArrayList()

    init {
        println("LOAD")
    }

    fun loadData() {
        val data = childManagementService.listChildren().map(::ChildTableRow)
        items.setAll(data)
    }
//    private val selectedItem: ObjectProperty<ChildTableRow>

    // property accessor methods
}