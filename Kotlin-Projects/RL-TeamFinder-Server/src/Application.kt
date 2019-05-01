package com.nick

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.sessions.*
import com.fasterxml.jackson.databind.*
import com.nick.database.ConnectionManager
import com.nick.database.Players
import com.nick.database.Teams
import com.nick.database.Users
import com.nick.model.Player
import com.nick.model.Team
import com.nick.model.basicPlayer
import io.ktor.jackson.*
import io.ktor.features.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit = io.ktor.server.tomcat.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    Database.connect("jdbc:mysql://teamfinderdb.cs48qmknn4om.us-east-2.rds.amazonaws.com:3306/TeamFinder",
        driver = "com.mysql.jdbc.Driver", user = "TheDude", password = "theguesswho123")

    install(Sessions) {
        cookie<MySession>("MY_SESSION") {
            cookie.extensions["SameSite"] = "lax"
        }
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    val client = HttpClient(Apache) {
    }

    routing {
        get("/Players") {
            lateinit var players: List<basicPlayer>

            transaction {
                players = Players.selectAll().map { basicPlayer(it[Players.playerID], it[Players.name],
                    it[Players.skill], it[Players.status]) }
            }

            call.respond(players)
        }

        post("/insert/Player") {
            val player = call.receive<Player>()
            transaction {
                Players.insert {
                    it[name] = player.name
                    it[steamLink] = player.steamLink
                    it[skill] = player.skill
                    it[status] = player.status
                }
                Players.slice(Players.playerID).select{Players.name eq player.name}.withDistinct()
                    .map { it[Players.playerID] }.forEach { player.id = it }
                Users.insert {
                    it[id] = player.id
                    it[username] = player.username
                    it[password] = player.password
                }
                commit()
            }
            call.respond("Player profile successfully added.")
        }

        post("/insert/team") {
            val team = call.receive<Team>()
            transaction {
                Teams.insert {
                    it[name] = team.name
                }
            }
        }

        get("/session/increment") {
            val session = call.sessions.get<MySession>() ?: MySession()
            call.sessions.set(session.copy(count = session.count + 1))
            call.respondText("Counter is ${session.count}. Refresh to increment.")
        }
    }
}

data class MySession(val count: Int = 0)

