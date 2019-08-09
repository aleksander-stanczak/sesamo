package pl.waw.sezamkowo.desktop

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import org.apache.logging.log4j.LogManager
import pl.waw.sezamkowo.desktop.common.View
import java.io.IOException
import java.net.URL
import java.util.*

@View
class MainController : Initializable {
    override fun initialize(location: URL?, resources: ResourceBundle?) {
    }

    @FXML
    fun openChildren() {
        openWindow("/fxml/child/child.fxml", "Dzieci", javaClass)
    }

    @FXML
    fun presence() {
        openWindowResize("presence/presence.fxml", "Obecność", javaClass)
    }

    @FXML
    fun journal() {
        openWindow("journal/journal.fxml", "Dziennik", javaClass)
    }

    @FXML
    fun settlement() {
        openWindow("settlement/settlement.fxml", "Rozliczenia", javaClass)
    }
}

fun openWindow(resourcePath: String, title: String, clazz: Class<*>) {
    try {
        val root = DesktopApplication.createFxmlLoader().load<Parent>(clazz.getResourceAsStream(resourcePath))
        val scene = Scene(root)
        val stage = Stage()
        stage.title = title
        stage.scene = scene
        stage.show()
    } catch (e: IOException) {
        val log = LogManager.getLogger()
        log.error("Could not open window!", e)
    }
}

fun openWindowResize(resourcePath: String, title: String, clazz: Class<*>) {
    try {
        val root = FXMLLoader.load<Parent>(clazz.getResource(resourcePath))
        val scene = Scene(root)
        val stage = Stage()
        stage.title = title
        stage.scene = scene
        stage.width = 658.0
        stage.height = 698.0
        stage.show()
    } catch (e: IOException) {
        val log = LogManager.getLogger()
        log.error("Could not open window!", e)
    }
}