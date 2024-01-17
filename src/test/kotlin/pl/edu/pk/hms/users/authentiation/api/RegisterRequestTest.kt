package pl.edu.pk.hms.users.authentiation.api

import jakarta.validation.Validation
import jakarta.validation.Validator
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.edu.pk.hms.users.authentiation.api.dto.RegisterRequest


private const val VALID_EMAIL = "test@email.com"
private const val VALID_PASSWORD = "password"
private const val VALID_PHONE_NUMBER = "+48123456789"

class RegisterRequestTest {

    @ParameterizedTest
    @MethodSource("successfulRequestCreationParameters")
    fun `should create registration request when {3}`(
        email: String,
        password: String,
        phoneNumber: String?,
        condition: String
    ) {
        //given
        val registerRequest = RegisterRequest(email, password, phoneNumber)
        val validator: Validator = Validation.buildDefaultValidatorFactory().validator

        //when
        val violations = validator.validate(registerRequest)

        //then
        assertTrue(violations.isEmpty())
    }

    @ParameterizedTest
    @MethodSource("failedRequestCreationParameters")
    fun `should throw error when {3}`(email: String, password: String, phoneNumber: String?) {
        //given
        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val registerRequest = RegisterRequest(email, password, phoneNumber)

        //when
        val violations = validator.validate(registerRequest)

        //then
        assertTrue(violations.isNotEmpty(), "Expected violations of validation but was empty")
    }

    companion object {
        @JvmStatic
        fun successfulRequestCreationParameters(): List<Arguments> = listOf(
            Arguments.of(VALID_EMAIL, VALID_PASSWORD, VALID_PHONE_NUMBER, "password and phone number, valid email"),
            Arguments.of(VALID_EMAIL, VALID_PASSWORD, null, "when empty phone number"),
        )

        @JvmStatic
        fun failedRequestCreationParameters(): List<Arguments> = listOf(
            Arguments.of(VALID_EMAIL, "", null, "empty password"),
            Arguments.of("", VALID_PASSWORD, null, "empty email")
        )
    }
}