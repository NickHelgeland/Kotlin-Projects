package com.nick.database

import org.jetbrains.exposed.sql.Database
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object ConnectionManager {

    lateinit var connection: Database

    init
    {
        try
        {
            connection = Database.connect("jdbc:mysql://teamfinderdb.cs48qmknn4om.us-east-2.rds.amazonaws.com:3306",
                driver = "com.mysql.jdbc.Driver", user = "TheDude", password = "theguesswho123")
        }
        catch (e: SQLException)
        {
            e!!.printStackTrace()
        }
        catch (e: ClassNotFoundException)
        {
            e!!.printStackTrace()
        }
    }
}