package testutils

import com.fasterxml.jackson.databind.JsonNode
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

class PaginatedResponse<T>(
    content: List<T>,
    number: Int,
    size: Int,
    totalElements: Long?,
    pageable: JsonNode,
    last: Boolean,
    totalPages: Int,
    sort: JsonNode,
    first: Boolean,
    numberOfElements: Int
) : PageImpl<T>(content, PageRequest.of(number, size), totalElements!!)