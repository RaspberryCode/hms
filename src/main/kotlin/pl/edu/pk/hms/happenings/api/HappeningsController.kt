package pl.edu.pk.hms.happenings.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import pl.edu.pk.hms.happenings.Happening
import pl.edu.pk.hms.happenings.HappeningsManagementService
import pl.edu.pk.hms.happenings.HappeningsReadService
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
        summary = "Get all happenings",
        responses = [
            ApiResponse(responseCode = "200", description = "Happenings found"),
            ApiResponse(responseCode = "401", description = "Only allowed for authenticated users")
        ]
    )
    fun findAll(): List<Happening> = happeningsReadService.findAll()

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
    fun create(request: CreateHappeningRequest): Happening = happeningsManagementService.create(request.toDomain())

}