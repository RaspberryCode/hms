package pl.edu.pk.hms.users.details.api.dto

import pl.edu.pk.hms.users.details.dao.UserDetails

data class UserDetailsResponse(
    var email: String,
    var phoneNumber: String?,
    val notificationsPreferences: NotificationsPreferencesDto
) {
    companion object {
        fun fromDomain(userDetails: UserDetails): UserDetailsResponse {
            return UserDetailsResponse(
                userDetails.email,
                userDetails.phoneNumber,
                NotificationsPreferencesDto.fromDomain(userDetails.notificationsPreferences)
            )
        }
    }
}