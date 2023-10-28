package pl.edu.pk.hms.users.authentiation

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/")
@SecurityRequirement(name = "bearer")
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping("register")
    fun register(@RequestBody request: RegisterRequest): AuthenticationResponse {
        return AuthenticationResponse(
            token = authenticationService.register(request)
        )
    }

    @PostMapping("login")
    fun login(@RequestBody request: AuthenticationRequest): AuthenticationResponse {
        return AuthenticationResponse(
            token = authenticationService.login(request)
        )
    }
}
