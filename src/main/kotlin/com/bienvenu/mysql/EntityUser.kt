package com.bienvenu.mysql

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object EntityUser:Table<Nothing>(tableName = "user") {
    val id = int(name = "id").primaryKey()
    val first = varchar(name = "first")
    val last = varchar(name = "last")
    val dob = varchar(name = "dob")
    val gender = varchar(name = "gender")
}
