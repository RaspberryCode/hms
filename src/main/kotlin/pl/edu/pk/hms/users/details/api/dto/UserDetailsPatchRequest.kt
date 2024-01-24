package pl.edu.pk.hms.users.details.api.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import pl.edu.pk.hms.happenings.District

data class UserDetailsPatchRequest(
    @field:Email(message = "Email should be valid")
    val email: String,
    @field:Pattern(
        regexp = "^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}\$",
        message = "Phone number should be valid and contain country code (e.g. +48 for Poland)"
    )
    val phoneNumber: String?,
    val notificationsPreferences: UserPreferencesDto,
    val districts: Set<District>
)
