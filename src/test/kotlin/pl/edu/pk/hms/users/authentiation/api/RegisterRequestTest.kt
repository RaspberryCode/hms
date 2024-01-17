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

    @ParameterizedTest(name = "{3}")
    @MethodSource("successfulRequestCreationParameters")
    fun `should pass validation when `(
        email: String,
        password: String,
        phoneNumber: String?,
        condition: String
    ) {
        //given
        val registerRequest = RegisterRequest(email, password, phoneNumber, null)
        val validator: Validator = Validation.buildDefaultValidatorFactory().validator

        //when
        val violations = validator.validate(registerRequest)

        //then
        assertTrue(violations.isEmpty())
    }

    @ParameterizedTest(name = "{3}")
    @MethodSource("failedRequestCreationParameters")
    fun `should fail validation when `(
        email: String,
        password: String,
        phoneNumber: String?,
        condition: String
    ) {
        //given
        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.validator
        val registerRequest = RegisterRequest(email, password, phoneNumber, null)

        //when
        val violations = validator.validate(registerRequest)

        //then
        assertTrue(violations.isNotEmpty(), "Expected violations of validation but was empty")
    }

    companion object {
        @JvmStatic
        fun successfulRequestCreationParameters(): List<Arguments> =
            listOf(
                Arguments.of(VALID_EMAIL, VALID_PASSWORD, VALID_PHONE_NUMBER, "valid password, phone number and email"),
                Arguments.of(VALID_EMAIL, VALID_PASSWORD, null, "empty phone number"),
            )

        @JvmStatic
        fun failedRequestCreationParameters(): List<Arguments> =
            listOf(
                Arguments.of(VALID_EMAIL, "", null, "empty password"),
                Arguments.of("", VALID_PASSWORD, null, "empty email")
            )
    }
}