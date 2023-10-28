package pl.edu.pk.hms.users.authentiation

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import pl.edu.pk.hms.users.authentiation.annotations.IsAdmin

@Configuration
class SecurityIntegrationTestConfiguration

@Profile("integration-test")
@RestController
internal class TestAdminController {
    @GetMapping("/admin-endpoint")
    @IsAdmin
    fun adminEndpoint(): `fun` {
        return ResponseEntity.ok("Admin Access Granted")
    }
}