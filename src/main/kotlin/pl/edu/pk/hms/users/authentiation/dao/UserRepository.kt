package pl.edu.pk.hms.users.authentiation.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import pl.edu.pk.hms.users.User
import java.util.*

interface UserRepository : JpaRepository<User, Long> {

    fun findUserByProfile_Email(email: String): Optional<User>

    @Query("select (count(u) > 0) from User u where u.profile.email = ?1")
    fun existsByProfile_Email(email: String): Boolean
}
