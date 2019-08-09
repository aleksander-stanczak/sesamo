package pl.waw.sezamkowo.core.infrastructure.child

import com.mongodb.MongoClient
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.test.context.junit4.SpringRunner
import pl.waw.sezamkowo.core.domain.child.ChildRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.core.SimpleMongoDbFactory
import org.springframework.data.authentication.UserCredentials
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import pl.waw.sezamkowo.core.domain.child.Child
import pl.waw.sezamkowo.core.domain.child.NurseryType
import pl.waw.sezamkowo.core.domain.child.Parent
import java.time.LocalDate


@RunWith(SpringRunner::class)
@SpringBootTest(classes = [ChildRepositoryImplTest.MongoConfiguration::class])
class ChildRepositoryImplTest{

    @Autowired
    private lateinit var repository: ChildRepository

    @Test
    fun addChild(){
        val parent1 = Parent("Iwonka", "599", "Retmanska", "stanczakiwona@gmail.com")
        val parent2 = Parent("Zbysio", "310", "Jasieniec", "zbigniew@gmail.com")

        val stubChild = Child(name = "Aleks",
                surname = "Stanczak",
                parent1 = parent1,
                parent2 = parent2,
                dateBirth = LocalDate.now(),
                entryDate = LocalDate.now(),
                nurseryType = NurseryType.NURSERY,
                graduateDate = null)
        repository.save(stubChild)
    }

    @ComponentScan("pl.waw.sezamkowo.core")
    @EnableMongoRepositories("pl.waw.sezamkowo.core")
    @SpringBootConfiguration
    class MongoConfiguration: AbstractMongoConfiguration() {

        override fun getDatabaseName(): String {
            return "sesamo"
        }

        override fun mongoClient(): MongoClient {
            return MongoClient("127.0.0.1", 27017)
        }

        override fun getMappingBasePackage(): String {
            return "pl.waw.sezamkowo.core"
        }
    }
}