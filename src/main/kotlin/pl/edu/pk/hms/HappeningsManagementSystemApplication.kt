package pl.edu.pk.hms

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder
import pl.edu.pk.hms.users.Role
import pl.edu.pk.hms.users.User
import pl.edu.pk.hms.users.authentiation.dao.UserRepository

@SpringBootApplication
class HappeningsManagementSystemApplication {
    @Bean
    fun init(userRepository: UserRepository, passwordEncoder: PasswordEncoder): CommandLineRunner {
        return CommandLineRunner {
            userRepository.save(
                User(
                    email = "admin@admin.com",
                    _password = passwordEncoder.encode("admin"),
                    phoneNumber = "+48123456789",
                    role = Role.ADMIN
                )
            )
            userRepository.save(
                User(
                    email = "user@user.com",
                    _password = passwordEncoder.encode("user"),
                    phoneNumber = "+48987654321",
                    role = Role.USER
                )
            )
        }
    }
}

fun main(args: Array<String>) {
    runApplication<HappeningsManagementSystemApplication>(*args)
}

