package pl.edu.pk.hms.users.authentiation.api.dto

import jakarta.validation.constraints.Email

class AuthenticationRequest(
    @Email(message = "Email should be valid")
    val email: String,
    val password: String
)
