package com.mika.webcstarter

import org.slf4j.LoggerFactory
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
class SampleResource {

    @PostMapping("/submit")
    fun submit(@RequestBody req: SubmitRequest): ResponseEntity<SubmitResponse> {
        log.info("Submit: $req")
        return ResponseEntity.ok(SubmitResponse(message = "Submit successful!"))
    }

    companion object {
        val log = LoggerFactory.getLogger(SampleResource::class.java)
    }
}

data class SubmitRequest(
    var type: String?,
    var name: String?
)

data class SubmitResponse(
        var message: String?
)
