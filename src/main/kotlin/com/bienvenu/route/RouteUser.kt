package com.bienvenu.route


import com.bienvenu.controller.UserController
import com.bienvenu.model.User
import com.bienvenu.mysql.DbConnection
import com.bienvenu.mysql.entity.EntityUser
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
        get("/bienvenu"){
            call.respondText("Welcome to Bienvenu")
        }

        post("/register")
        {
//            val controller = UserController()
//            controller.registerUser()


            val user: User = call.receive()
            val noOfRowsAffected = db.insert(EntityUser)
            {
                set(it.nom, user.nom)
                set(it.prenom, user.prenom)
                set(it.naissance, user.naissance)
                set(it.genre, user.genre)
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
                        nom = it[EntityUser.nom],
                        prenom = it[EntityUser.prenom],
                        genre = it[EntityUser.genre],
                        naissance = it[EntityUser.naissance]
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
//            call.respondText("OK")

            println("Getting user by ID: ${call.parameters["id"]}")
            val pararmId = call.parameters["id"]
            val id = pararmId?.toInt() ?: -1

            val user = db.from(EntityUser)
                .select()
                .where {
                    EntityUser.id eq id
                }
                .map {
                    User(
                        id = it[EntityUser.id],
                        nom = it[EntityUser.nom],
                        prenom = it[EntityUser.prenom],
                        genre = it[EntityUser.genre],
                        naissance = it[EntityUser.naissance]
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
                set(it.nom, userReq.nom)
                set(it.prenom, userReq.prenom)
                set(it.naissance, userReq.naissance)
                set(it.genre, userReq.genre)

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