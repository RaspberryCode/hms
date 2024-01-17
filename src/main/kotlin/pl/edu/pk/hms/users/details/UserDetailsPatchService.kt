package pl.edu.pk.hms.users.details

import org.springframework.stereotype.Service
import pl.edu.pk.hms.users.details.api.dto.UserPreferencesDto
import pl.edu.pk.hms.users.details.dao.UserProfile
import pl.edu.pk.hms.users.details.dao.UserDetailsRepository

@Service
class UserDetailsPatchService(private val userDetailsRepository: UserDetailsRepository) {
    fun getUserDetails(userId: Long): UserProfile {
        return userDetailsRepository.findById(userId).orElseThrow()
    }

    fun updateUserDetails(
        userId: Long,
        phoneNumber: String?,
        email: String?,
        notificationsPreferences: UserPreferencesDto?
    ): UserProfile {
        return userDetailsRepository.findById(userId)
            .orElseThrow()
            .apply {
                this.email = email ?: this.email
                this.phoneNumber = phoneNumber ?: this.phoneNumber
                this.preferences = notificationsPreferences?.toDomain() ?: this.preferences
            }.apply {
                userDetailsRepository.save(this)
            }
    }
}