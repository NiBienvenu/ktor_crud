package com.bienvenu

import com.bienvenu.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    println("Application")
    configureRouting()
}
