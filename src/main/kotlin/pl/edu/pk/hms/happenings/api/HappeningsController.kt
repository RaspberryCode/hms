package pl.edu.pk.hms.happenings.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import pl.edu.pk.hms.happenings.Happening
import pl.edu.pk.hms.happenings.HappeningsManagementService
import pl.edu.pk.hms.happenings.HappeningsReadService
import pl.edu.pk.hms.happenings.api.dto.CreateHappeningRequest
import pl.edu.pk.hms.happenings.api.dto.HappeningResponse
import pl.edu.pk.hms.users.authentiation.annotations.IsAdmin
import pl.edu.pk.hms.users.authentiation.annotations.IsUser

@RestController
@RequestMapping("api/happenings")
@Tag(name = "Happenings")
class HappeningsController(
    val happeningsReadService: HappeningsReadService,
    val happeningsManagementService: HappeningsManagementService
) {

    @GetMapping
    @IsUser
    @Operation(
        summary = "Get happenings paginated",
        responses = [
            ApiResponse(responseCode = "200", description = "Happenings found"),
            ApiResponse(responseCode = "401", description = "Only allowed for authenticated users")
        ]
    )
    fun findAll(pageable: Pageable): Page<HappeningResponse> =
        happeningsReadService.findAll(pageable)
            .map { HappeningResponse(it) }


    @GetMapping("/{id}")
    @IsUser
    @Operation(
        summary = ("Get happening by id"),
        responses = [
            ApiResponse(responseCode = "200", description = "Happening found"),
            ApiResponse(responseCode = "404", description = "Happening not found"),
            ApiResponse(responseCode = "401", description = "Only allowed for authenticated users")
        ]
    )
    fun findById(@PathVariable id: Long): Happening = happeningsReadService.findById(id)
        .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Not found happening for provided id") }

    @PostMapping
    @IsAdmin
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(
        summary = "Create new happening",
        responses = [
            ApiResponse(responseCode = "201", description = "Happening created"),
            ApiResponse(responseCode = "401", description = "Only allowed for admins")
        ]
    )
    fun create(@RequestBody @Valid request: CreateHappeningRequest): Happening =
        happeningsManagementService.create(request.toDomain())

    @PutMapping("/{id}")
    @IsAdmin
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(
        summary = "Update happening",
        responses = [
            ApiResponse(responseCode = "200", description = "Happening updated"),
            ApiResponse(responseCode = "401", description = "Only allowed for admins")
        ]
    )
    fun update(@RequestBody @Valid request: CreateHappeningRequest, @PathVariable id: Long): Happening {
        return happeningsManagementService.update(id, request.toDomain())
    }
}