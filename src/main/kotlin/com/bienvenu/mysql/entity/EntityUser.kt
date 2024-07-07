package com.bienvenu.mysql.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object EntityUser:Table<Nothing>(tableName = "user") {
    val id = int(name = "id").primaryKey()
    val first = varchar(name = "nom")
    val last = varchar(name = "prenom")
    val dob = varchar(name = "dob")
    val gender = varchar(name = "gender")
}
