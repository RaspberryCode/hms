package pl.edu.pk.hms.users

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import pl.edu.pk.hms.users.details.dao.UserProfile

@Entity
@Table(name = "users")
open class User(
    @Id
    @GeneratedValue
    var id: Long? = null,
    @Column(nullable = false)
    var encryptedPassword: String,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: Role,
    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, optional = false)
    var profile: UserProfile?
) : UserDetails {
    constructor() : this(null, "", Role.USER, null)

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("ROLE_${role.name}"))
    }

    override fun getPassword(): String {
        return encryptedPassword
    }

    override fun getUsername(): String {
        return profile!!.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}