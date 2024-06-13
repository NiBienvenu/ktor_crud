package com.bienvenu.plugins

import com.bienvenu.model.Priority
import com.bienvenu.model.Task
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {

        staticResources("/task-ui", "task-ui")

        get("/") {
            call.respondText("Hello Bienvenu!")
        }
        val a = 8
        fun Task.taskAsRow() = """
        <tr>
            <td>$name</td><td>$description</td><td>$priority</td>
        </tr>
        """.trimIndent()
        fun List<Task>.tasksAsTable() = this.joinToString(
            prefix = "<table rules=\"all\">",
            postfix = "</table>",
            separator = "\n",
            transform = Task::taskAsRow
        )
        val tasks = mutableListOf(
            Task("cleaning", "Clean the house", Priority.LOW),
            Task("gardening", "Mow the lawn", Priority.MEDIUM),
            Task("shopping", "Buy the groceries", Priority.HIGH),
            Task("painting", "Paint the fence", Priority.URGENT)
        )
//        get("/tasks") {
//            call.respondText(
//                contentType = ContentType.parse("text/html"),
//                text = """
//                <h3>TODO:</h3>
//                <ol>
//                    <li>A table of all the $a tasks</li>
//                    <li>A form to submit new tasks</li>
//                </ol>
//                """.trimIndent()
//            )
//        }

        get("/tasks") {
            call.respondText(
                contentType = ContentType.parse("text/html"),
                text = tasks.tasksAsTable()
            )
        }
    }
}

