package pl.edu.pk.hms.happenings

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import pl.edu.pk.hms.happenings.api.dto.HappeningResponse
import pl.edu.pk.hms.happenings.dao.HappeningsRepository
import pl.edu.pk.hms.users.Role
import testutils.IntegrationTest
import testutils.PaginatedResponse
import testutils.WebClient

class HappeningsIntegrationTest(
    @Autowired val happeningsRepository: HappeningsRepository,
    @Autowired val webClient: WebClient
) : IntegrationTest() {
    @BeforeEach
    fun setUp() {
        webClient.removeAllUsers()
        happeningsRepository.deleteAll()
        happeningsRepository.saveAll(
            listOf(
                Happening(
                    name = "Happening 1",
                    district = District.CZYZYNY,
                    description = "Description 1"
                ),
                Happening(
                    name = "Happening 2",
                    district = District.DEBNIKI,
                    description = "Description 2"
                ),
                Happening(
                    name = "Happening 3",
                    district = District.PODGORZE,
                    description = "Description 3"
                )
            )
        )
    }

    @Test
    fun `should return all happenings`() {
        // given
        val (user, userPassword) = webClient.createUser()
        val responseTypeRef = object : ParameterizedTypeReference<PaginatedResponse<HappeningResponse>>() {}
        // when
        webClient.asAuthenticatedUser(user.username, userPassword) { token ->
            val response =
                webClient.get(token, "/api/happenings", responseTypeRef)
            // then
            assertTrue(response.statusCode.is2xxSuccessful)
            assertEquals(3, response.body!!.numberOfElements)
        }
    }

    @Test
    fun `get happening by id`() {
        // given
        val (user, userPassword) = webClient.createUser()
        val happening = happeningsRepository.findAll().first()
        // when
        webClient.asAuthenticatedUser(user.username, userPassword) { token ->
            val response =
                webClient.get(token, "/api/happenings/${happening.id}", HappeningResponse::class.java)
            // then
            assertTrue(response.statusCode.is2xxSuccessful)
            assertEquals(happening.name, response.body!!.name)
            assertEquals(happening.district, response.body!!.district)
            assertEquals(happening.description, response.body!!.description)
        }
    }

    @Test
    fun `should create new happening`() {
        // given
        val (user, userPassword) = webClient.createUser(Role.ADMIN)
        val newHappening = Happening(
            name = "Happening 4",
            district = District.PODGORZE,
            description = "Description 4"
        )
        // when
        webClient.asAuthenticatedUser(user.username, userPassword) { token ->
            val response =
                webClient.post(token, "/api/happenings", newHappening, HappeningResponse::class.java)
            // then
            assertTrue(response.statusCode.is2xxSuccessful)
            assertEquals(newHappening.name, response.body!!.name)
            assertEquals(newHappening.district, response.body!!.district)
            assertEquals(newHappening.description, response.body!!.description)
            assertNotNull(response.body!!.id)
        }
    }

    @Test
    fun `should update happening`() {
        // given
        val (user, userPassword) = webClient.createUser(Role.ADMIN)
        val happening = happeningsRepository.findAll().first()
        val updatedHappening = happening.copy(
            name = "Happening 4",
            district = District.PODGORZE,
            description = "Description 4"
        )
        // when
        webClient.asAuthenticatedUser(user.username, userPassword) { token ->
            val response =
                webClient.put(token, "/api/happenings/${happening.id}", updatedHappening, HappeningResponse::class.java)
            // then
            assertTrue(response.statusCode.is2xxSuccessful)
            assertEquals(updatedHappening.name, response.body!!.name)
            assertEquals(updatedHappening.district, response.body!!.district)
            assertEquals(updatedHappening.description, response.body!!.description)
            assertEquals(happening.id, response.body!!.id)
        }
    }
}