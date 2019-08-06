package pl.waw.sezamkowo.sesamo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SesamoApplication

fun main(args: Array<String>) {
	runApplication<SesamoApplication>(*args)
}
