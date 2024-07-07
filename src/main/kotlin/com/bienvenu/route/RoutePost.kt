package com.bienvenu.route

import com.bienvenu.mysql.DbConnection
import com.bienvenu.util.GenericResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.database.Database

fun Application.routePost(){
    val db :Database = DbConnection.getDatabaseInstance()

    routing {
        get("/getpost") {

            call.respond(
                HttpStatusCode.OK,
                GenericResponse(isSuccess = true, data = "OK")
            )
        }

    }
}


