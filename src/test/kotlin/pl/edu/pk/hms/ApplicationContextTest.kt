package pl.edu.pk.hms

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

@SpringBootTest
@SpringJUnitConfig(classes = [TestHappeningsManagementSystemApplication::class])
class HappeningsManagementSystemApplicationTests {
    @Test
    fun contextLoads() {
    }
}
