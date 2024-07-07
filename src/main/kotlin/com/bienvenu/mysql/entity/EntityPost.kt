package com.bienvenu.mysql.entity

import org.ktorm.schema.*

object EntityPost: Table<Nothing>(tableName = "post") {
    val id = int(name = "id").primaryKey()
    val userId = int(name = "user_id").referenceTable
    val title = varchar(name = "title")
    val content = text(name = "description")
    val createdAt = timestamp(name = "created_at")
    val updatedAt = timestamp(name = "updated_at")
}