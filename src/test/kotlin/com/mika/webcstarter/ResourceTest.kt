package com.mika.webcstarter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
class ResourceTest {
	@Autowired
	lateinit var mvc: MockMvc

	@Test
	fun resourceReturnsSelect() {
		mvc.perform(get("/api/select-options")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk)
				.andDo { log.info("${it.response.contentAsString}") }
	}

	@Test
	fun resourceReturnsComboBox() {
		mvc.perform(get("/api/combo-box-options")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk)
				.andDo { log.info("${it.response.contentAsString}") }
	}

	@Test
	fun resourceAcceptsSubmit() {
		val req = SubmitRequest(
				date = LocalDate.now(),
				select = "Finnish",
				comboBox = "EUR")
		val mapper = ObjectMapper()
		mapper.registerModule(JavaTimeModule())
		mapper.enable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID)
		val json = mapper.writeValueAsString(req)
		mvc.perform(post("/api/submit")
						.content(json)
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk)
	}

	companion object {
		val log = LoggerFactory.getLogger(ResourceTest::class.java)
	}
}
