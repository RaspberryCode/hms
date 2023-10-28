package pl.edu.pk.hms.users.authentiation

data class RegisterRequest(
    val email: String,
    val password: String,
    val phoneNumber: String,
)
