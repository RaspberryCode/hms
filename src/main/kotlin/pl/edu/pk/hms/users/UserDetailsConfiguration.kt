package pl.edu.pk.hms.users

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import pl.edu.pk.hms.users.authentiation.dao.UserRepository

@Configuration
class UserDetailsConfiguration(private val userRepository: UserRepository) {
    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { username: String? ->
            userRepository.findByEmail(username!!)
                .orElseThrow { UsernameNotFoundException("User not found") }
        }
    }
}
