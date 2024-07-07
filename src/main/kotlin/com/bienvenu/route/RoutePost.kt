package com.bienvenu.route

import com.bienvenu.model.Post
import com.bienvenu.mysql.DbConnection
import com.bienvenu.mysql.entity.EntityPost
import com.bienvenu.mysql.entity.EntityUser
import com.bienvenu.util.GenericResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.database.Database
import org.ktorm.dsl.*

fun Application.routePost(){
    val db :Database = DbConnection.getDatabaseInstance()

    routing {
        get("/getpost") {

            val post = db
                .from(EntityPost)
                .innerJoin(EntityUser, on = EntityPost.user_id eq EntityUser.id)
                .select()
                post.map {
                    println("post ........")
                    val row = it[EntityPost.user_id]
                  println("row -=> $row")
                }
            call.respond(
                HttpStatusCode.OK,
                GenericResponse(isSuccess = true, data ="ok")
            )
        }

    }
}


