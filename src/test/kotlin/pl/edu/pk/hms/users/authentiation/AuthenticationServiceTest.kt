package pl.edu.pk.hms.users.authentiation

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.context.ApplicationEventPublisher
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import pl.edu.pk.hms.config.JwtService
import pl.edu.pk.hms.notifications.handlers.events.UserRegistered
import pl.edu.pk.hms.users.Role
import pl.edu.pk.hms.users.User
import pl.edu.pk.hms.users.authentiation.dao.UserRepository

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

    @Spy
    lateinit var eventPublisher: ApplicationEventPublisher

    @InjectMocks
    lateinit var authenticationService: AuthenticationService

    @BeforeEach
    fun setUp() {
        userRepository.deleteAll()
    }

    @Test
    fun `after registration password should not be stored in plain text`() {
        // given
        val email = "example@example.com"
        val password = "password"
        whenever(userRepository.existsByProfile_Email(anyString())).thenReturn(false)
        whenever(userRepository.save(any<User>())).thenReturn(User().apply { id = 1L })
        whenever(jwtService.generate(any(), any())).thenReturn("token")
        whenever(passwordEncoder.encode(anyString())).thenReturn("encodedPassword")
        doNothing().whenever(eventPublisher).publishEvent(any())

        // when
        authenticationService.register(
            email = email,
            password = password,
            phoneNumber = null,
            userPreferences = null
        )

        // then
        val userCaptor = argumentCaptor<User>()
        verify(userRepository).save(userCaptor.capture())
        verify(eventPublisher).publishEvent(any<UserRegistered>())
        assertNotEquals(password, userCaptor.firstValue.password, "Password should not be stored in plain text.")
    }

    @Test
    fun `registered user has role USER`() {
        // given
        val email = "example@example.com"
        val password = "password"
        whenever(userRepository.existsByProfile_Email(anyString())).thenReturn(false)
        whenever(userRepository.save(any<User>())).thenReturn(User().apply { id = 1L })
        whenever(jwtService.generate(any(), any())).thenReturn("token")
        whenever(passwordEncoder.encode(anyString())).thenReturn("encodedPassword")
        doNothing().whenever(eventPublisher).publishEvent(any())

        // when
        authenticationService.register(
            email = email,
            password = password,
            phoneNumber = null,
            userPreferences = null
        )

        // then
        val userCaptor = argumentCaptor<User>()
        verify(userRepository).save(userCaptor.capture())
        verify(eventPublisher).publishEvent(any<UserRegistered>())
        assertEquals(Role.USER, userCaptor.firstValue.role, "Only user with role USER should be allowed to register.")
    }

    @Test
    fun `should not allow registration when email is already taken`() {
        // given
        val email = "example@example.com"
        val password = "password"
        whenever(userRepository.existsByProfile_Email(anyString())).thenReturn(true)

        // when
        assertThrows(IllegalArgumentException::class.java) {
            authenticationService.register(
                email = email,
                password = password,
                phoneNumber = null,
                userPreferences = null
            )
        }
    }
}