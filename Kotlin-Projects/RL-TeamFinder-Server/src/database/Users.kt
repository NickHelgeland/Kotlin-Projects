package com.nick.database

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = integer("playerID")
    val username = varchar("username", 50).primaryKey()
    val password = varchar("password", 50)
}