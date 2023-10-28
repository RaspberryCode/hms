package pl.edu.pk.hms.user.authentiation

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import pl.edu.pk.hms.config.JwtService
import pl.edu.pk.hms.user.Role
import pl.edu.pk.hms.user.User
import pl.edu.pk.hms.user.UserRepository

@Service
class AuthenticationService(
    private val jwtService: JwtService,
    private val userRepository: UserRepository,
    private val authenticationManager: AuthenticationManager
) {
    fun register(request: RegisterRequest): String {
        userRepository.findByEmail(request.email)
            .ifPresent { throw IllegalArgumentException("Email address already assigned to account.") }
        val user = User(
            email = request.email,
            _password = request.password,
            phoneNumber = request.phoneNumber,
            role = Role.USER
        )
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
        val user = userRepository.findByEmail(request.email)
            .orElseThrow { IllegalArgumentException("Invalid email or password.") }
        return jwtService.generate(user)
    }
}