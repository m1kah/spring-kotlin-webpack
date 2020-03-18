package com.mika.webcstarter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebcStarterApplication

fun main(args: Array<String>) {
	runApplication<WebcStarterApplication>(*args)
}
