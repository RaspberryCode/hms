package pl.edu.pk.hms.users.details.api.dto

import pl.edu.pk.hms.users.details.dao.UserPreferences

data class UserPreferencesDto(
    val email: Boolean = false,
    val sms: Boolean = false,
    val push: Boolean = false
) {
    fun toDomain(): UserPreferences {
        return UserPreferences(
            emailNotifications = email,
            smsNotifications = sms,
            pushNotifications = push
        )
    }

    companion object {
        fun fromDomain(userPreferences: UserPreferences): UserPreferencesDto {
            return UserPreferencesDto(
                email = userPreferences.emailNotifications,
                sms = userPreferences.smsNotifications,
                push = userPreferences.pushNotifications
            )
        }
    }
}