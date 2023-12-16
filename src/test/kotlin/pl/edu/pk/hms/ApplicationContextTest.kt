package pl.edu.pk.hms

import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import utils.IntegrationTest


class ApplicationContextTest @Autowired constructor(
    private val applicationContext: ApplicationContext
) : IntegrationTest() {
    @Test
    fun `Should load application context`() {
        applicationContext shouldNotBe null
    }
}