package pl.edu.pk.hms.happenings

import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/happenings")
class HappeningsController(
    val happeningsReadService: HappeningsReadService,
    val happeningsManagementService: HappeningsManagementService
) {
    @GetMapping
    fun findAll(): List<Happening> = happeningsReadService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Optional<Happening> = happeningsReadService.findById(id)

    @PostMapping
    fun create(request: CreateHappeningRequest): Happening = happeningsManagementService.create(request.toDomain())

}