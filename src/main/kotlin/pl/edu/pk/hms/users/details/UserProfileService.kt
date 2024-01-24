package pl.edu.pk.hms.users.details

import org.springframework.stereotype.Service
import pl.edu.pk.hms.happenings.District
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
        notificationsPreferences: UserPreferencesDto?,
        districts: Set<District>?
    ): UserProfile {
        return userProfilesRepository.findById(userId)
            .orElseThrow()
            .apply {
                this.email = email ?: this.email
                this.phoneNumber = phoneNumber ?: this.phoneNumber
                this.preferences = notificationsPreferences?.toDomain() ?: this.preferences
                this.districts = districts ?: this.districts
            }.apply {
                userProfilesRepository.save(this)
            }
    }

    fun getUsersByDistrict(district: District): Set<UserProfile> {
        return userProfilesRepository.findByDistrictsContains(district)
    }
}