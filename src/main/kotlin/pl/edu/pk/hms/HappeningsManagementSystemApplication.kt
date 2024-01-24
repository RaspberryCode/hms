package pl.edu.pk.hms

import jakarta.annotation.Generated
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder
import pl.edu.pk.hms.happenings.District
import pl.edu.pk.hms.users.Role
import pl.edu.pk.hms.users.User
import pl.edu.pk.hms.users.authentiation.dao.UserRepository
import pl.edu.pk.hms.users.details.dao.UserPreferences
import pl.edu.pk.hms.users.details.dao.UserProfile

@Generated
@SpringBootApplication
@EnableFeignClients
class HappeningsManagementSystemApplication {
    @Bean
    fun init(userRepository: UserRepository, passwordEncoder: PasswordEncoder): CommandLineRunner {
        return CommandLineRunner {
            val admin = User(
                encryptedPassword = passwordEncoder.encode("admin"),
                role = Role.ADMIN,
                profile = null
            )
            val adminProfile = UserProfile(
                email = "admin@admin.com",
                phoneNumber = "123456789",
                preferences = UserPreferences(),
                user = admin,
                districts = setOf(District.PRADNIK_BIALY)
            )
            admin.profile = adminProfile
            val user = User(
                encryptedPassword = passwordEncoder.encode("user"),
                role = Role.USER,
                profile = null
            )
            val userProfile = UserProfile(
                email = "user@user.com",
                phoneNumber = "123456789",
                preferences = UserPreferences(),
                user = user,
                districts = setOf(District.CZYZYNY)
            )
            user.profile = userProfile
            userRepository.saveAll(listOf(admin, user))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<HappeningsManagementSystemApplication>(*args)
}

