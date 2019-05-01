package com.nick.model

class Team {

    var id: Int = 0;

    var name: String = ""

    var player1: Int = 0

    var player2: Int = 0

    var player3: Int = 0

    constructor() {}

    constructor(id: Int, name: String, player1: Int, player2: Int, player3: Int){
        this.id = id
        this.name = name
        this.player1 = player1
        this.player2 = player2
        this.player3 = player3
    }

}