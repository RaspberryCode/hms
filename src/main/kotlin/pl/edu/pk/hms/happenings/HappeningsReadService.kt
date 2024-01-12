package pl.edu.pk.hms.happenings

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import pl.edu.pk.hms.happenings.dao.HappeningsReadRepository
import java.util.*

@Service
class HappeningsReadService(val happeningsReadRepository: HappeningsReadRepository) {
    fun findAll(pageable: Pageable): Page<Happening> =
        happeningsReadRepository.findAll(pageable)
    fun findById(id: Long): Optional<Happening> = happeningsReadRepository.findById(id)
}