package com.mika.webcstarter

import com.neovisionaries.i18n.CurrencyCode
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
class SampleResource {
    @GetMapping("/select-options")
    fun selectOptions(): List<String> = listOf("Finnish", "Swedish", "Sami", "Karelian")

    @GetMapping("/combo-box-options")
    fun comboBoxOptions(): List<CurrencyCode> = CurrencyCode.values().toList()

    @PostMapping("/submit")
    fun submit(@RequestBody req: SubmitRequest): ResponseEntity<Unit> {
        log.info("Submit: $req")
        return ResponseEntity.ok().build()
    }

    companion object {
        val log = LoggerFactory.getLogger(SampleResource::class.java)
    }
}

data class SubmitRequest(
    var date: LocalDate?,
    var select: String?,
    var comboBox: String?
)
