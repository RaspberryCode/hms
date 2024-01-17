package pl.edu.pk.hms.users.authentiation

import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import pl.edu.pk.hms.config.JwtService
import pl.edu.pk.hms.users.User
import pl.edu.pk.hms.users.authentiation.api.dto.RegisterRequest
import pl.edu.pk.hms.users.authentiation.dao.UserRepository
import java.util.*

@ExtendWith(MockitoExtension::class)
class AuthenticationServiceTest {

    @Mock
    lateinit var jwtService: JwtService

    @Spy
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var authenticationManager: AuthenticationManager

    @Mock
    lateinit var passwordEncoder: PasswordEncoder

    @InjectMocks
    lateinit var authenticationService: AuthenticationService

    @Test
    fun `after registration password should not be stored in plain text`() {
        // given
        val email = "example@example.com"
        val password = "password"
        whenever(userRepository.findByEmail(anyString())).thenReturn(Optional.empty())
        whenever(jwtService.generate(any(), any())).thenReturn("token")
        whenever(passwordEncoder.encode(anyString())).thenReturn("encodedPassword")

        // when
        authenticationService.register(RegisterRequest(email, password, null))

        // then
        val userCaptor = argumentCaptor<User>()
        verify(userRepository).save(userCaptor.capture())
        assertNotEquals(password, userCaptor.firstValue.password, "Password should not be stored in plain text.")
    }
}