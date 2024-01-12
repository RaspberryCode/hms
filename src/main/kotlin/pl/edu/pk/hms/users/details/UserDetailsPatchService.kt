package pl.edu.pk.hms.users.details

import org.springframework.stereotype.Service
import pl.edu.pk.hms.users.details.dao.UserDetails
import pl.edu.pk.hms.users.details.dao.UserDetailsPatchRepository

@Service
class UserDetailsPatchService(private val userDetailsPatchRepository: UserDetailsPatchRepository) {
    fun getUserDetails(userId: Long): UserDetails {
        return userDetailsPatchRepository.findById(userId).orElseThrow()
    }

    fun updateUserDetails(userId: Long, phoneNumber: String?, email: String?): UserDetails {
        val userDetails = userDetailsPatchRepository.findById(userId).orElseThrow()
        userDetails.email = email ?: userDetails.email
        userDetails.phoneNumber = phoneNumber ?: userDetails.phoneNumber
        return userDetailsPatchRepository.save(userDetails)
    }
}