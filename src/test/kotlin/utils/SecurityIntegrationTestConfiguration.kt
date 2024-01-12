package utils

import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import pl.edu.pk.hms.users.authentiation.annotations.IsAdmin
import pl.edu.pk.hms.users.authentiation.annotations.IsUser

@Configuration
class SecurityIntegrationTestConfiguration {
    @RestController
    class TestAdminController {
        @GetMapping("/admin-endpoint")
        @IsAdmin
        fun adminEndpoint(): ResponseEntity<String> {
            return ResponseEntity.ok("Admin Access Granted")
        }
    }

    @RestController
    class TestUserController {
        @GetMapping("/user-endpoint")
        @IsUser
        fun userEndpoint(): ResponseEntity<String> {
            return ResponseEntity.ok("User Access Granted")
        }
    }
}
