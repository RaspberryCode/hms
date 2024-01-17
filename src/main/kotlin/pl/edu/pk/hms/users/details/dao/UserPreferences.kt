package pl.edu.pk.hms.users.details.dao

import jakarta.persistence.Embeddable

@Embeddable
class UserPreferences(
    var emailNotifications: Boolean = false,
    var smsNotifications: Boolean = false,
    var pushNotifications: Boolean = false,
)
