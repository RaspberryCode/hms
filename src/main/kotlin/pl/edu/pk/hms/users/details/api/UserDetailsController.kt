package pl.edu.pk.hms.users.details.api

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
import pl.edu.pk.hms.users.details.UserDetailsPatchService
import pl.edu.pk.hms.users.details.api.dto.UserDetailsPatchRequest
import pl.edu.pk.hms.users.details.api.dto.UserDetailsResponse

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Details")
class UserDetailsController(private val userDetailsPatchService: UserDetailsPatchService) {

    @GetMapping
    fun getDetails(authentication: Authentication): ResponseEntity<UserDetailsResponse> {
        val user = authentication.principal as User
        val userDetails = userDetailsPatchService.getUserDetails(user.id!!)
        val response = UserDetailsResponse(userDetails.email, userDetails.phoneNumber)
        return ResponseEntity.ok(response)
    }

    @PatchMapping
    fun updateDetails(
        @Valid @RequestBody request: UserDetailsPatchRequest,
        authentication: Authentication
    ): ResponseEntity<UserDetailsResponse> {
        val user = authentication.principal as User
        val userId = checkNotNull(user.id) { "Problem with granting access to user details"}
        val updateUserDetails = userDetailsPatchService.updateUserDetails(userId, request.phoneNumber, request.email)
        val response = UserDetailsResponse(updateUserDetails.email, updateUserDetails.phoneNumber)
        return ResponseEntity.ok(response)
    }
}