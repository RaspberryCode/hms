package pl.edu.pk.hms.users.details.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.edu.pk.hms.users.User
import pl.edu.pk.hms.users.authentiation.annotations.IsUser
import pl.edu.pk.hms.users.details.UserProfileService
import pl.edu.pk.hms.users.details.api.dto.UserDetailsPatchRequest
import pl.edu.pk.hms.users.details.api.dto.UserDetailsResponse

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Details")
class UserDetailsController(private val userProfileService: UserProfileService) {

    @GetMapping
    @IsUser
    @Operation(
        summary = "Get user details",
        description = "Returns user details",
        responses = [
            ApiResponse(responseCode = "200", description = "User details found"),
            ApiResponse(responseCode = "401", description = "Only allowed for authenticated users")
        ]
    )
    fun getDetails(authentication: Authentication): ResponseEntity<UserDetailsResponse> {
        val user = authentication.principal as User
        require(user.id != null) { "Problem with granting access to user details" }
        val userDetails = userProfileService.getUserProfile(user.id!!)
            .orElseThrow()

        return ResponseEntity.ok(UserDetailsResponse.fromDomain(userDetails))
    }

    @PatchMapping
    @IsUser
    @Operation(
        summary = "Update user details",
        description = "Updates user details",
        responses = [
            ApiResponse(responseCode = "200", description = "User details updated"),
            ApiResponse(responseCode = "401", description = "Only allowed for authenticated users")
        ]
    )
    fun updateDetails(
        @Valid @RequestBody request: UserDetailsPatchRequest,
        authentication: Authentication
    ): ResponseEntity<UserDetailsResponse> {
        val user = authentication.principal as User
        val userId = checkNotNull(user.id) { "Problem with granting access to user details" }

        val updateUserDetails = userProfileService.updateUserProfile(
            userId = userId,
            phoneNumber = request.phoneNumber,
            email = request.email,
            notificationsPreferences = request.notificationsPreferences,
            districts = request.districts
        )
        return ResponseEntity.ok(UserDetailsResponse.fromDomain(updateUserDetails))
    }
}