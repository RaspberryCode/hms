package pl.edu.pk.hms.users.authentiation.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.edu.pk.hms.users.authentiation.AuthenticationService

@RestController
@RequestMapping("/api/")
@Tag(name = "Authentication")
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping("register")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(
        summary = "Register new user",
        responses = [
            ApiResponse(responseCode = "201", description = "User registered"),
            ApiResponse(responseCode = "400", description = "Invalid request")
        ]
    )
    fun register(@RequestBody @Valid request: RegisterRequest): AuthenticationResponse {
        return AuthenticationResponse(
            token = authenticationService.register(request)
        )
    }

    @PostMapping("login")
    @Operation(
        summary = "Login user",
        responses = [
            ApiResponse(responseCode = "200", description = "User logged in"),
            ApiResponse(responseCode = "400", description = "Invalid request")
        ]
    )
    fun login(@RequestBody @Valid request: AuthenticationRequest): AuthenticationResponse {
        return AuthenticationResponse(
            token = authenticationService.login(request)
        )
    }
}
