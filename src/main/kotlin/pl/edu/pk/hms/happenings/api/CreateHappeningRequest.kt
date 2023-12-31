package pl.edu.pk.hms.happenings.api

import jakarta.validation.constraints.Size
import pl.edu.pk.hms.happenings.District
import pl.edu.pk.hms.happenings.Happening

class CreateHappeningRequest(
    @Size(min = 3, max = 64, message = "Name should be between 3 and 50 characters")
    private var name: String,
    private var district: District,
    @Size(min = 3, max = 500, message = "Description should be between 3 and 512 characters")
    private var description: String
) {
    fun toDomain() = Happening(
        name = name,
        district = district,
        description = description
    )
}
