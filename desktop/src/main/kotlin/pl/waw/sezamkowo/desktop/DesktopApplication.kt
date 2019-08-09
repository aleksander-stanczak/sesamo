package pl.waw.sezamkowo.desktop

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.util.Callback
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@EnableMongoRepositories("pl.waw.sezamkowo")
@Configuration
class MongoConfiguration

@Configuration
class ThreadPoolConfiguration {

    @Bean
    fun executorService(): ExecutorService {
        return Executors.newFixedThreadPool(6)
    }
}

@ComponentScan("pl.waw.sezamkowo")
@SpringBootApplication
class DesktopApplication : Application() {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(DesktopApplication::class.java)
        }

        private lateinit var springContext: ConfigurableApplicationContext

        fun createFxmlLoader() = FXMLLoader().apply {
            controllerFactory = Callback { springContext.getBean(it) }
        }
    }


    private var rootNode: Parent? = null

    @Throws(Exception::class)
    override fun init() {
        springContext = SpringApplication.run(DesktopApplication::class.java)
    }

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val fxmlLoader = createFxmlLoader()
        fxmlLoader.location = javaClass.getResource("/fxml/main.fxml")
        rootNode = fxmlLoader.load<Parent>()

        primaryStage.title = "Sesamo"
        val scene = Scene(rootNode)
        primaryStage.scene = scene
        primaryStage.show()
    }

    override fun stop() {
        springContext!!.stop()
    }
}