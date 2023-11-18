package pl.edu.pk.hms.happenings.dao

import org.springframework.data.jpa.repository.JpaRepository
import pl.edu.pk.hms.happenings.Happening

interface HappeningsRepository : JpaRepository<Happening, Long>