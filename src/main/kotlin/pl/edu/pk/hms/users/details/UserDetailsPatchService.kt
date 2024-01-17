package pl.edu.pk.hms.users.details

import org.springframework.stereotype.Service
import pl.edu.pk.hms.users.details.api.dto.NotificationsPreferencesDto
import pl.edu.pk.hms.users.details.dao.UserDetails
import pl.edu.pk.hms.users.details.dao.UserDetailsRepository

@Service
class UserDetailsPatchService(private val userDetailsRepository: UserDetailsRepository) {
    fun getUserDetails(userId: Long): UserDetails {
        return userDetailsRepository.findById(userId).orElseThrow()
    }

    fun updateUserDetails(
        userId: Long,
        phoneNumber: String?,
        email: String?,
        notificationsPreferences: NotificationsPreferencesDto?
    ): UserDetails {
        return userDetailsRepository.findById(userId)
            .orElseThrow()
            .apply {
                this.email = email ?: this.email
                this.phoneNumber = phoneNumber ?: this.phoneNumber
            }.apply {
                userDetailsRepository.save(this)
            }
    }
}