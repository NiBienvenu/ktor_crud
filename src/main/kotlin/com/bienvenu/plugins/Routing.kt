package com.bienvenu.plugins

import com.bienvenu.route.routeUser
import io.ktor.server.application.*

fun Application.configureRouting() {
    routeUser()
}
