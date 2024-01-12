package pl.edu.pk.hms.happenings.dao

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class HappeningsReadRepository(val happeningsRepository: HappeningsRepository) {
    fun findAll(pageable: Pageable) =
        happeningsRepository.findAll(pageable)
    fun findById(id: Long) = happeningsRepository.findById(id)
}
