package pl.edu.pk.hms.users.details.dao

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table


@Entity
@Table(name = "users")
class UserDetails(
    @Id
    val id: Long,
    var email: String,
    var phoneNumber: String?,
    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL])
    var notificationsPreferences: NotificationsPreferences
)