package pl.edu.pk.hms.users.authentiation.dao

import org.springframework.data.jpa.repository.JpaRepository
import pl.edu.pk.hms.users.User
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>
}
