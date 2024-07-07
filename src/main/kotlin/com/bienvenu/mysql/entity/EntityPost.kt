package com.bienvenu.mysql.entity

import org.ktorm.schema.*

object EntityPost: Table<Nothing>(tableName = "post") {
    val id = int(name = "id").primaryKey()
    val user_id = int(name = "user_id")
    val title = varchar(name = "title")
    val content = text(name = "description")
    val created_at = timestamp(name = "created_at")
    val updated_at = timestamp(name = "updated_at")
}