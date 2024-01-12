package pl.edu.pk.hms

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import utils.IntegrationTest


class ApplicationContextTest(
    private val applicationContext: ApplicationContext
) : IntegrationTest() {
    @Test
    fun `Should load application context`() {
        assertNotNull(applicationContext)
    }
}