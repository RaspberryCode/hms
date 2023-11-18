package pl.edu.pk.hms.happenings.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.edu.pk.hms.happenings.District
import pl.edu.pk.hms.users.authentiation.annotations.IsUser

@RestController
@RequestMapping("api/locations")
@Tag(name = "Locations")
class LocationsController {
    @GetMapping
    @IsUser
    @Operation(
        summary = "Get all available locations",
        description = "Returns all available locations",
        responses = [
            ApiResponse(responseCode = "200", description = "Locations found"),
            ApiResponse(responseCode = "401", description = "Only allowed for authenticated users")
        ]
    )
    fun getAvailableLocations(): List<String> {
        return District.values().map { it.name }
    }
}