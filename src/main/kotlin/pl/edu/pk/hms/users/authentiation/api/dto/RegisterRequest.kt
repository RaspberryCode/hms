package pl.edu.pk.hms.users.authentiation.api.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class RegisterRequest(
    @field:NotNull(message = "Email cannot be null")
    @field:NotEmpty(message = "Email cannot be empty")
    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(message = "Email should be valid")
    val email: String,
    @field:NotBlank(message = "Password cannot be blank")
    @field:Size(min = 8, message = "Password must be at least 8 characters long")
    val password: String,
    @field:Pattern(
        regexp = "^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}\$",
        message = "Phone number should be valid and contain country code (e.g. +48 for Poland)"
    )
    val phoneNumber: String?,
)
