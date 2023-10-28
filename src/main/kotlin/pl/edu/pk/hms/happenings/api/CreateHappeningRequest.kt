package pl.edu.pk.hms.happenings

class CreateHappeningRequest(
    var name: String,
    var district: District,
    var description: String){

    fun toDomain() = Happening(
        name = name,
        district = district,
        description = description
    )
}
