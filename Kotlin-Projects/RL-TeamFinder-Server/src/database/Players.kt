package com.nick.database

import com.nick.database.Users.autoIncrement
import com.nick.database.Users.primaryKey
import org.jetbrains.exposed.sql.Table

object Players : Table() {
    val playerID = integer("playerID").autoIncrement().primaryKey()
    val name = varchar("player_name", 50)
    val steamLink = varchar("steam_link", 100)
    val skill = varchar("skill_group", 20)
    val team = integer("team")
    val status = varchar("player_status", 50)
}