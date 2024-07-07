package com.bienvenu.route

import com.bienvenu.mysql.DbConnection
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.ktorm.database.Database

fun Application.routePost(){
    val db :Database = DbConnection.getDatabaseInstance()

    routing {
        get("/getpost") {
                println("get post ....")
        }

    }
}


