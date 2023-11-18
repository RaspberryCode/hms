package pl.edu.pk.hms.happenings

import org.jboss.logging.Logger
import org.springframework.stereotype.Service
import pl.edu.pk.hms.happenings.dao.HappeningsRepository
import pl.edu.pk.hms.users.authentiation.AuthenticationService

@Service
class HappeningsManagementService(
    val happeningsRepository: HappeningsRepository,
    val authenticationService: AuthenticationService
) {
    private val logger = Logger.getLogger(javaClass)
    fun create(happening: Happening): Happening {
        logger.info("Creating happening: $happening by ${authenticationService.getUsername()}")
        return happeningsRepository.save(happening)
    }
}
