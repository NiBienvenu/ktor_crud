package com.bienvenu.model

class Task (
    val name: String,
    val description: String,
    val priority: Priority,
){



    fun Task.taskAsRow() = """
        <tr>
            <td>$name</td><td>$description</td><td>$priority</td>
        </tr>
        """.trimIndent()

}

