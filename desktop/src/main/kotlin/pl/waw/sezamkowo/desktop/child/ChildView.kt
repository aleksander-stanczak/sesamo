package pl.waw.sezamkowo.desktop.child

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ChoiceBox
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.layout.AnchorPane
import org.springframework.beans.factory.annotation.Autowired
import pl.waw.sezamkowo.core.domain.child.NurseryType
import pl.waw.sezamkowo.desktop.common.View
import java.net.URL
import java.time.LocalDate
import java.util.*

@View
class ChildView : Initializable {

    @FXML
    lateinit var nameColumn: TableColumn<ChildTableRow, String>
    @FXML
    lateinit var dateBirthColumn: TableColumn<ChildTableRow, LocalDate>
    @FXML
    lateinit var parent1Column: TableColumn<ChildTableRow, String>
    @FXML
    lateinit var parent2Column: TableColumn<ChildTableRow, String>

    @FXML
    lateinit var table: TableView<ChildTableRow>
        @FXML lateinit var nurseryTypeChoice: ChoiceBox<NurseryType>
//    @FXML lateinit var monthChoice: ChoiceBox<YearMonth>
    @FXML
    lateinit var root: AnchorPane

    @Autowired
    private lateinit var viewModel: ChildViewModel

//    val data = FXCollections.observableArrayList<ChildInfo>()
//    val monthData = FXCollections.observableArrayList<YearMonth>()
//    val dao = Repositories.childInfoDao

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        loadStylesheets()
        setupTable()
        setupChoices()
        loadData()
    }

        private fun setupChoices() {
        nurseryTypeChoice.setKotlinConverter { it.prettyName }
//        nurseryTypeChoice.items.setAll(NurseryType.values().toList())
//        monthChoice.showingProperty().addListener { bean, old, new ->
//            if (new && monthChoice.items.isEmpty()) {
//                fxTask({
//                    monthChoice.hide()
//                    monthChoice.disableProperty().value = true
//                    val children = Repositories.childrenDao.findAll()
//                    children.flatMap { it.presenceMonths.map { it.date } }.plus(YearMonth.now()).distinct()
//                }, {
//                    monthData.setAll(it)
//                    val sortedList = SortedList<YearMonth>(monthData, { first, second ->
//                        second.asSingleValue().compareTo(first.asSingleValue())
//                    })
//                    monthChoice.items.setAll(sortedList)
//                    monthChoice.disableProperty().value = false
//                })
//            }
//        }
    }

    private fun loadStylesheets() {
        root.stylesheets.add("fxml/styles.css")
    }

    private fun loadData() {
        viewModel.loadData()
    }

    private fun setupTable() {

        table.items = viewModel.items

        nameColumn.setCellValueFactory { it.value.nameProperty }
        dateBirthColumn.setCellValueFactory { it.value.dateBirthProperty }
        parent1Column.setCellValueFactory { it.value.parent1Property }
        parent2Column.setCellValueFactory { it.value.parent2Property }
//
//        dateBirthColumn.setCellFactory {
//            object : TableCell<ChildInfo, LocalDate>() {
//                override fun updateItem(item: LocalDate?, empty: Boolean) {
//                    super.updateItem(item, empty)
//                    val currentDay = LocalDate.now().dayOfYear
//                    if (item != null && (item.dayOfYear - currentDay) in 0..7) {
//                        styleClass.add("warning")
//                    } else {
//                        styleClass.remove("warning")
//                    }
//                    text = item?.toString() ?: ""
//                }
//            }
//        }
//
//        childTable.setOnMouseClicked { ev ->
//            if (ev.clickCount == 2) {
//                edit()
//            }
//        }
//
//        childTable.items = SortedList<ChildInfo>(data, { first, second ->
//            first.surname.compareTo(second.surname)
//        })
    }
//
//    @FXML fun add() {
//        val item = ChildDialog(null).showAndWait()
//        item.ifPresent {
//            Repositories.childrenDao.save(
//                    Child(it.name, it.surname, it.parent1, it.parent2, it.dateBirth, mutableListOf(), it.graduateDate, it.entryDate, it.nurseryType)
//            )
//            data.setAll(dao.findAll())
//        }
//    }
//
//    @FXML fun edit() {
//        val item = ChildDialog(childTable.selectionModel.selectedItem).showAndWait()
//        item.ifPresent {
//            val child = Repositories.childrenDao.findOne(it.id)
//            val updatedChild = child.copy(it.name, it.surname, it.parent1, it.parent2, it.dateBirth, nurseryType = it.nurseryType, graduateDate = it.graduateDate, entryDate = it.entryDate)
//            Repositories.childrenDao.save(updatedChild)
//            data.setAll(dao.findAll())
//        }
//    }
//
//    @FXML fun delete() {
//        val item = childTable.selectionModel.selectedItem
//        dao.delete(item)
//        data.setAll(dao.findAll())
//    }
//
//    @FXML fun presence() {
//        MainController.currentChild = childTable.selectionModel.selectedItem
//        MainController.INSTANCE.presence()
//    }
//
//    @FXML
//    fun holidays() {
//        openWindow("holiday/holiday.fxml", "Dni wolne", javaClass)
//    }

//    @FXML
//    fun generateAllChildrenPresenceReport() {
//        exportToCsv(monthChoice.value, nurseryTypeChoice.value)
//    }
//
//    fun exportToCsv(month: YearMonth, nurseryType: NurseryType) {
//        val csvFile = File("raport_obecnosci_${month.month}_${month.year}_${nurseryType.prettyName.replace(" ", "")}.csv")
//        val children = Repositories.childrenDao.allAttending(month).filter { it.nurseryType == nurseryType }
//        Desktop.open(csvFile)
//        csvFile.writer(Charset.forName("windows-1250")).use {
//            it.appendln("Data, Razem, ${children.joinToString(",")}")
//            val presences = children.map { it.getMonth(month) }
//            val days = getAllMonthDays(month)
//            days.forEach { day ->
//                it.append("$day")
//                val wasPresentList = presences.map { wasPresent(it, day) }
//                it.append(",${wasPresentList.count { it }},")
//                it.appendln(wasPresentList.map { toSigns(it) }.joinToString(","))
//            }
//            it.flush()
//        }
//    }
//
//    private fun toSigns(it: Boolean): String = if (it) {
//        "+"
//    } else
//        ""
//
//    private fun wasPresent(presences: PresenceMonth?, day: LocalDate): Boolean = presences?.presences?.any { it.date == day } ?: false
//
//    private fun getAllMonthDays(month: YearMonth): List<LocalDate> {
//        val calendar = Calendar.getInstance()
//        calendar.set(month.year, month.month, 0)
//        val numDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
//        val firstDay = month.toLocalDate()
//        val days = mutableListOf<LocalDate>()
//        repeat(numDays) {
//            days.add(firstDay.plusDays(it.toLong()))
//        }
//        return days
//    }
}