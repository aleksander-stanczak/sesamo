package pl.waw.sezamkowo.desktop.child

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import pl.waw.sezamkowo.core.application.child.ChildManagementService
import pl.waw.sezamkowo.desktop.common.LongTaskWithData
import pl.waw.sezamkowo.desktop.common.ViewModel
import java.util.concurrent.ExecutorService

@ViewModel
class ChildViewModel(private val childManagementService: ChildManagementService,
                     private val executorService: ExecutorService) {
    val items: ObservableList<ChildTableRow> = FXCollections.observableArrayList()

    fun loadData() {
        executorService.submit(
                LongTaskWithData(
                        action = {
                            childManagementService.listChildren().map(::ChildTableRow)
                        },
                        onFinish = {
                            items.setAll(it)
                        }
                )
        )
    }
}
//    private val selectedItem: ObjectProperty<ChildTableRow>

// property accessor methods