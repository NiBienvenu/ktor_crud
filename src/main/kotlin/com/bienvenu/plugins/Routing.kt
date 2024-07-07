package com.bienvenu.plugins

import com.bienvenu.route.routeUser
import com.bienvenu.route.routePost
import io.ktor.server.application.*

fun Application.configureRouting() {
    routeUser()
    routePost()
}
