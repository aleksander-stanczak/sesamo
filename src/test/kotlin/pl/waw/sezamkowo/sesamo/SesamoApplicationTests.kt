package pl.waw.sezamkowo.sesamo

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import pl.waw.sezamkowo.core.domain.child.ChildRepository

@RunWith(SpringRunner::class)
@SpringBootTest
//@TestPropertySource(
//		  locations = "classpath:application-integrationtest.properties")
class SesamoApplicationTests {

	@Test
	fun contextLoads() {
	}

}
