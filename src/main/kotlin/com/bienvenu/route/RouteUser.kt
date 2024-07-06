package com.bienvenu.route


import com.bienvenu.model.User
import com.bienvenu.mysql.DbConnection
import com.bienvenu.mysql.EntityUser
import com.bienvenu.util.GenericResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.database.Database
import org.ktorm.dsl.*

fun Application.routeUser() {
    val db: Database = DbConnection.getDatabaseInstance()
    routing {
        get("/")
        {
            call.respondText("Welcome to Ktor Mysql")
        }

        post("/register")
        {
            val user: User = call.receive()
            val noOfRowsAffected = db.insert(EntityUser)
            {
                set(it.first, user.first)
                set(it.last, user.last)
                set(it.dob, user.dob)
                set(it.gender, user.gender)
            }

            if (noOfRowsAffected > 0) {
                //success
                call.respond(
                    HttpStatusCode.OK,
                    GenericResponse(isSuccess = true, data = "$noOfRowsAffected rows are affected")
                )
            } else {
                //fail
                call.respond(
                    HttpStatusCode.BadRequest,
                    GenericResponse(isSuccess = false, data = "Error to register the user")
                )
            }
        }

        get("/users")
        {
            val list = db.from(EntityUser)
                .select()
                .map {
                    User(
                        id = it[EntityUser.id],
                        first = it[EntityUser.first],
                        last = it[EntityUser.last],
                        dob = it[EntityUser.dob],
                        gender = it[EntityUser.gender]
                    )
                }

            if(list.isNotEmpty())
                call.respond(
                    HttpStatusCode.OK,
                    GenericResponse(isSuccess = true, data = list)
                )
            else
                call.respond(
                    HttpStatusCode.OK,
                    GenericResponse(isSuccess = false, data = null)
                )
        }

        get("/user/{id}")
        {
            println("=====================================")
            println("Getting user by ID: ${call.parameters["id"]}")
            println("=====================================")
            val userIdStr = call.parameters["id"]
            val userIdInt = userIdStr?.toInt() ?: -1

            val user = db.from(EntityUser)
                .select()
                .where {
                    EntityUser.id eq userIdInt
                }
                .map {
                    User(
                        id = it[EntityUser.id],
                        first = it[EntityUser.first],
                        last = it[EntityUser.last],
                        dob = it[EntityUser.dob],
                        gender = it[EntityUser.gender]
                    )
                }
                .firstOrNull()

            if(user != null )
                call.respond(
                    HttpStatusCode.OK,
                    GenericResponse(isSuccess = true, data = user)
                )
            else
                call.respond(
                    HttpStatusCode.OK,
                    GenericResponse(isSuccess = false, data = null)
                )
        }

        put("/user/{id}")
        {
            val userIdStr = call.parameters["id"]
            val userIdInt = userIdStr?.toInt() ?: -1
            val userReq: User = call.receive()

            val noOfRowsAffected = db.update(EntityUser)
            {
                set(it.first, userReq.first)
                set(it.last, userReq.last)
                set(it.dob, userReq.dob)
                set(it.gender, userReq.gender)

                where {
                    it.id eq userIdInt
                }
            }

            if (noOfRowsAffected > 0) {
                //success
                call.respond(
                    HttpStatusCode.OK,
                    GenericResponse(isSuccess = true, data = "$noOfRowsAffected rows are affected")
                )
            } else {
                //fail
                call.respond(
                    HttpStatusCode.BadRequest,
                    GenericResponse(isSuccess = false, data = "Error to update the user")
                )
            }
        }


        delete("/user/{id}")
        {
            val userIdStr = call.parameters["id"]
            val userIdInt = userIdStr?.toInt() ?: -1

            val noOfRowsAffected = db.delete(EntityUser)
            {
                it.id eq userIdInt
            }

            if (noOfRowsAffected > 0) {
                //success
                call.respond(
                    HttpStatusCode.OK,
                    GenericResponse(isSuccess = true, data = "$noOfRowsAffected rows are affected")
                )
            } else {
                //fail
                call.respond(
                    HttpStatusCode.BadRequest,
                    GenericResponse(isSuccess = false, data = "Error to delete the user")
                )
            }
        }
    }
}