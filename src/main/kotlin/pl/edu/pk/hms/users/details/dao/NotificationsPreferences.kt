package pl.edu.pk.hms.users.details.dao

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "notifications_preferences")
class NotificationsPreferences(
    @Id
    @GeneratedValue
    var id: Long? = null,
    var email: Boolean,
    var sms: Boolean,
    var push: Boolean,
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var user: UserDetails? = null
)
