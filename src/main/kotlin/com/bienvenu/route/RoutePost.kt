package com.bienvenu.route

import com.bienvenu.model.Post
import com.bienvenu.mysql.DbConnection
import com.bienvenu.mysql.entity.EntityPost
import com.bienvenu.mysql.entity.EntityPost.updated_at
import com.bienvenu.mysql.entity.EntityUser
import com.bienvenu.util.GenericResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
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
        get("/getpost/{id}") {
            val pararmId = call.parameters["id"]
            val id = pararmId?.toInt()?: -1

            val post = db
               .from(EntityPost)
               .innerJoin(EntityUser, on = EntityPost.user_id eq EntityUser.id)
               .select()
               .where { EntityPost.id eq id }
               .limit(1)
            call.respond(
                HttpStatusCode.OK,
                GenericResponse(isSuccess = true, data = Post(
//                    id = post[EntityPost.id],
//                    userId = post[EntityUser.id],
//                    title = post[EntityPost.title],
//                    content = post[EntityPost.content],
//                    createdAt = post[EntityPost.created_at],
//                    updatedAt = post[EntityPost.updated_at]
                ))
            )
        }
        post("/createpost") {
            val post = call.receive<Post>()

            val inserted = db
               .insert(EntityPost) {
//                    it[user_id] = post.userId
//                    it[title] = post.title
//                    it[content] = post.description
//                    it[created_at] = post.createdAt
//                    it[updated_at] = post.updatedAt
                }

            call.respond(
                HttpStatusCode.Created,
                GenericResponse(isSuccess = inserted > 0, data = inserted)
            )
        }
        put("/updatepost/{id}") {
            val pararmId = call.parameters["id"]
            val id = pararmId?.toInt()?: -1
            val post = call.receive<Post>()

            val updated = db
               .update(EntityPost) {
//                    it[user_id] = p/ost.userId
//                    it[title] = post.title
//                    it[content] = post.content
//                    it[updated_at] = post.updatedAt
//                    where { EntityPost.id eq id   }
                }

        }
        delete("/deletepost/{id}") {
            val pararmId = call.parameters["id"]
            val id = pararmId?.toInt()?: -1

            val deleted = db
               .delete(EntityPost) {
                    EntityPost.id eq id
                }

            call.respond(
                HttpStatusCode.NoContent,
                GenericResponse(isSuccess = deleted > 0, data = deleted)
            )
        }

    }
}


