package pl.edu.pk.hms.users.details.api.dto

import pl.edu.pk.hms.users.details.dao.NotificationsPreferences

data class NotificationsPreferencesDto(
    val email: Boolean = false,
    val sms: Boolean = false,
    val push: Boolean = false
) {
    companion object {
        fun fromDomain(notificationsPreferences: NotificationsPreferences): NotificationsPreferencesDto {
            return NotificationsPreferencesDto(
                email = notificationsPreferences.email,
                sms = notificationsPreferences.sms,
                push = notificationsPreferences.push
            )
        }
    }
}