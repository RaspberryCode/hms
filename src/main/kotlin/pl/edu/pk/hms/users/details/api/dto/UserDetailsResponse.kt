package pl.edu.pk.hms.users.details.api.dto

import pl.edu.pk.hms.users.details.dao.UserProfile

data class UserDetailsResponse(
    var email: String,
    var phoneNumber: String?,
    val preferences: UserPreferencesDto
) {
    companion object {
        fun fromDomain(userProfile: UserProfile): UserDetailsResponse {
            return UserDetailsResponse(
                userProfile.email,
                userProfile.phoneNumber,
                UserPreferencesDto.fromDomain(userProfile.preferences)
            )
        }
    }
}