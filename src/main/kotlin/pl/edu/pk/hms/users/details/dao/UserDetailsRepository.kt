package pl.edu.pk.hms.users.details.dao

import org.springframework.data.jpa.repository.JpaRepository

interface UserDetailsRepository : JpaRepository<UserProfile, Long>