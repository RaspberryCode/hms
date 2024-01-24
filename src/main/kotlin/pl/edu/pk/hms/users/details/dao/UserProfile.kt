package pl.edu.pk.hms.users.details.dao

import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import pl.edu.pk.hms.happenings.District
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
    var user: User,
    @ElementCollection(targetClass = District::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_districts", joinColumns = [JoinColumn(name = "user_profile_id")])
    @Column(name = "district")
    @Enumerated(EnumType.STRING)
    var districts: Set<District> = emptySet()
) {
    constructor() : this(null, "", null, UserPreferences(), User(), emptySet())
}