package pl.edu.pk.hms

import com.zaxxer.hikari.HikariDataSource
import io.kotest.core.extensions.install
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.extensions.testcontainers.JdbcDatabaseContainerExtension
import org.junit.jupiter.api.Disabled
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@ActiveProfiles("integration")
@AutoConfigureMockMvc
@SpringBootTest
@Disabled("Refactor needed")
class ApplicationContextTest : FunSpec({
    val postgres = PostgreSQLContainer<Nothing>("postgres:16.0-alpine")
    val dataSource: HikariDataSource = install(JdbcDatabaseContainerExtension(postgres))
}) {
    init {
        extension(SpringExtension)
        test("Should load application context") {
            assert(true)
    }
}
}
//{

//    companion object {
//        @Container
//        @ServiceConnection
//          val postgres = PostgreSQLContainer<Nothing>("postgres:16.0-alpine")
//    }
//    @Test
//    fun contextLoads() {
//    }
//}
