package com.nick.model

class Player {

    var id: Int = 0

    var name: String = ""

    var steamLink: String = ""

    var skill: String = ""

    var team: Int = 0

    var status: String = ""

    var username: String = ""

    var password: String = ""

    constructor() {}

    constructor(id: Int, name: String, steamLink: String, skill: String, team: Int, status: String, username: String,
                password: String){
        this.id = id
        this.name = name
        this.steamLink = steamLink
        this.skill = skill
        this.team = team
        this.status = status
        this.username = username
        this.password = password
    }

}