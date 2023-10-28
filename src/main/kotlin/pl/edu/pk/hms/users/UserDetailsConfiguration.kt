package pl.edu.pk.hms.user

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

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
