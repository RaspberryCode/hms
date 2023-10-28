package pl.edu.pk.hms.users

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long>{
    fun findByEmail(email: String): Optional<User>
}
