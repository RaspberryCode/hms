package pl.edu.pk.hms.users.details.dao

import org.springframework.data.jpa.repository.JpaRepository
import pl.edu.pk.hms.happenings.District

interface UserProfilesRepository : JpaRepository<UserProfile, Long> {
    fun findByDistrictsContains(district: District): Set<UserProfile>
}