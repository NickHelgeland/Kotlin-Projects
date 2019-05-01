package com.nick.database

import org.jetbrains.exposed.sql.Table

object Teams : Table() {
    val id = integer("teamID").autoIncrement().primaryKey()
    val name = varchar("team_name", 50)
    val player1 = integer("player1")
    val player2 = integer("player2")
    val player3 = integer("player3")
}