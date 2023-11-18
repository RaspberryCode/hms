package pl.edu.pk.hms.happenings.dao

import org.springframework.stereotype.Repository

@Repository
class HappeningsReadRepository(val happeningsRepository: HappeningsRepository) {
    fun findAll() = happeningsRepository.findAll()
    fun findById(id: Long) = happeningsRepository.findById(id)
}
