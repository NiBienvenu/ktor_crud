package com.bienvenu.mysql.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object EntityUser:Table<Nothing>(tableName = "user") {
    val id = int(name = "id").primaryKey()
    val nom = varchar(name = "nom")
    val prenom = varchar(name = "prenom")
    val naissance = varchar(name = "naissance")
    val genre = varchar(name = "genre")
}
