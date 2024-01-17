package pl.edu.pk.hms.users.details.dao

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import pl.edu.pk.hms.users.User


@Entity
@Table(name = "user_profiles")
open class UserProfile(
    @Id
    var id: Long? = null,
    var email: String,
    var phoneNumber: String?,
    @Embedded
    var preferences: UserPreferences,
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    var user: User
) {
    protected constructor() : this(null, "", null, UserPreferences(), User())
}