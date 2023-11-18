package pl.edu.pk.hms.happenings

import org.springframework.stereotype.Service
import pl.edu.pk.hms.happenings.dao.HappeningsReadRepository
import java.util.*

@Service
class HappeningsReadService(val happeningsReadRepository: HappeningsReadRepository) {
    fun findAll(): List<Happening> = happeningsReadRepository.findAll()
    fun findById(id: Long): Optional<Happening> = happeningsReadRepository.findById(id)
}