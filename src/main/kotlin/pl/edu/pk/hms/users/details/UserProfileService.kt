package pl.edu.pk.hms.users.details

import org.springframework.stereotype.Service
import pl.edu.pk.hms.users.details.api.dto.UserPreferencesDto
import pl.edu.pk.hms.users.details.dao.UserProfile
import pl.edu.pk.hms.users.details.dao.UserProfilesRepository
import java.util.*

@Service
class UserProfileService(private val userProfilesRepository: UserProfilesRepository) {
    fun getUserProfile(userId: Long): Optional<UserProfile> =
        userProfilesRepository.findById(userId)

    fun updateUserProfile(
        userId: Long,
        phoneNumber: String?,
        email: String?,
        notificationsPreferences: UserPreferencesDto?
    ): UserProfile {
        return userProfilesRepository.findById(userId)
            .orElseThrow()
            .apply {
                this.email = email ?: this.email
                this.phoneNumber = phoneNumber ?: this.phoneNumber
                this.preferences = notificationsPreferences?.toDomain() ?: this.preferences
            }.apply {
                userProfilesRepository.save(this)
            }
    }
}