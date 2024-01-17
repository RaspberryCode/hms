package pl.edu.pk.hms.users.authentiation

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import pl.edu.pk.hms.config.JwtService
import pl.edu.pk.hms.users.Role
import pl.edu.pk.hms.users.User
import pl.edu.pk.hms.users.authentiation.api.dto.AuthenticationRequest
import pl.edu.pk.hms.users.authentiation.dao.UserRepository
import pl.edu.pk.hms.users.details.dao.UserPreferences
import pl.edu.pk.hms.users.details.dao.UserProfile

const val NO_USER: String = "No user"

@Service
class AuthenticationService(
    private val jwtService: JwtService,
    private val userRepository: UserRepository,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder
) {
    fun getUsername(): String {
        return SecurityContextHolder.getContext().authentication.name ?: NO_USER
    }

    fun register(
        email: String,
        password: String,
        phoneNumber: String?,
        userPreferences: UserPreferences?
    ): String {
        require(!userRepository.existsByProfile_Email(email)) {
            throw IllegalArgumentException("Email address already assigned to account.")
        }

        val user = User(
            encryptedPassword = passwordEncoder.encode(password),
            role = Role.USER,
            profile = null
        )
        val userProfile = UserProfile(
            email = email,
            phoneNumber = phoneNumber,
            preferences = userPreferences ?: UserPreferences(),
            user = user
        )
        user.profile = userProfile
        userRepository.save(user)
        return jwtService.generate(user)
    }

    fun login(request: AuthenticationRequest): String {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password
            )
        )
        val user = userRepository.findUserByProfile_Email(request.email)
            .orElseThrow { IllegalArgumentException("Invalid email or password.") }
        return jwtService.generate(user)
    }
}