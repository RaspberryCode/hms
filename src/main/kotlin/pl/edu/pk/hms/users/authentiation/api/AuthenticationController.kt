package pl.edu.pk.hms.users.authentiation.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import pl.edu.pk.hms.users.authentiation.AuthenticationService
import pl.edu.pk.hms.users.authentiation.api.dto.AuthenticationRequest
import pl.edu.pk.hms.users.authentiation.api.dto.AuthenticationResponse
import pl.edu.pk.hms.users.authentiation.api.dto.RegisterRequest

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
        val notificationsPreferences = request.notificationsPreferences?.toDomain()
        return AuthenticationResponse(
            token = authenticationService.register(
                email = request.email,
                password = request.password,
                phoneNumber = request.phoneNumber,
                userPreferences = notificationsPreferences
            )
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

//    TODO
//    https://cheatsheetseries.owasp.org/cheatsheets/Forgot_Password_Cheat_Sheet.html
//    @PostMapping("reset-password/{token}")
//    @Operation(
//        summary = "Reset password",
//        responses = [
//            ApiResponse(responseCode = "200", description = "Password reset"),
//            ApiResponse(responseCode = "400", description = "Invalid request")
//        ]
//    )
//    fun resetPassword(){
//
//    }
}
