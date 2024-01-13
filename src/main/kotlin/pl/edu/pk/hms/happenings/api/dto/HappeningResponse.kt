package pl.edu.pk.hms.happenings.api.dto

import pl.edu.pk.hms.happenings.District

data class HappeningResponse(
    val id: Long,
    val name: String,
    val district: District,
    val description: String
) {
    constructor(happening: pl.edu.pk.hms.happenings.Happening) : this(
        id = happening.id!!,
        name = happening.name,
        district = happening.district,
        description = happening.description
    )
}