package pl.waw.sezamkowo.desktop.common

import javafx.concurrent.Task
import org.slf4j.LoggerFactory

class LongTask(private val action: () -> Unit, private val onFinish: () -> Unit, private val onFail: (e: Throwable) -> Unit) : Task<Unit>() {
    init {

        setOnFailed { onFail(exception) }
        setOnSucceeded { onFinish.invoke() }
    }

    override fun call() {
        action.invoke()
    }
}

class LongTaskWithData<D>(
        private val action: () -> D,
        private val onFinish: (D) -> Unit,
        private val onFail: (e: Throwable) -> Unit = { log.error("Error during processing data:", it) }
) : Task<D>() {

    companion object {
        private val log = LoggerFactory.getLogger(LongTaskWithData::class.java)
    }

    init {
        setOnFailed { onFail(exception) }
        setOnSucceeded { onFinish.invoke(get()) }
    }

    override fun call(): D {
        return action.invoke()
    }
}