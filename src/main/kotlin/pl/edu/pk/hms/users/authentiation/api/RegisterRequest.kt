package pl.edu.pk.hms.users.authentiation.api

import jakarta.validation.constraints.*

data class RegisterRequest(
    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    val email: String,
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    val password: String,
    @Pattern(
//        regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}\$",
        regexp = "^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}\$",
        message = "Phone number should be valid and contain country code (e.g. +48 for Poland)"
    )
    val phoneNumber: String,
)
