package pl.edu.pk.hms.happenings

import jakarta.persistence.*

@Entity
@Table(name = "happenings")
data class Happening(
    @Id
    @GeneratedValue
    var id: Long? = null,
    @Column(unique = true, length = 64)
    var name: String,
    @Enumerated(EnumType.STRING)
    var district: District,
    @Column(length = 512)
    var description: String
)
