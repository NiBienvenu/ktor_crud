package com.bienvenu

import com.bienvenu.plugins.configureRouting
import com.bienvenu.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*

fun Application.module() {
    configureRouting()
    configureSerialization()
}

fun main() {
    embeddedServer(Tomcat, port = 8080, host = "192.168.56.1") {
        module()
    }.start(wait = true)
}
