package pl.edu.pk.hms.users.details.api.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern

data class UserDetailsPatchRequest(
    @field:NotEmpty(message = "Email cannot be empty")
    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(message = "Email should be valid")
    val email: String?,
    @field:Pattern(
        regexp = "^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}\$",
        message = "Phone number should be valid and contain country code (e.g. +48 for Poland)"
    )
    val phoneNumber: String?
)