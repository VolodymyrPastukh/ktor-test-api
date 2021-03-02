package com.jetbrains.handson.httpapi.db

import com.jetbrains.handson.httpapi.models.Worker
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.transaction


object DatabaseFactory {
    fun init(){
        Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")
        transaction {
            create(Workers)
            Workers.insert {
                it[name] = "Volodymyr"
                it[surname] = "Pastukh"
                it[email] = "volodymyrpastukh99@gmail.com"
                it[type] = "HUMAN"
            }

            Workers.insert {
                it[name] = "Volodymyr"
                it[surname] = "Lenin"
                it[email] = "lenin1917@mail.su"
                it[type] = "HUMAN"
            }

            Workers.insert {
                it[name] = "Volodymyr"
                it[surname] = "Putin"
                it[email] = "prostoVova@mail.ru"
                it[type] = "HUMAN"
            }
        }
    }

    fun getWorkers(): List<Worker> = transaction {
        Workers.selectAll().map{ Workers.toWorker(it)}
    }
}

object Workers: Table(){
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 255)
    val surname: Column<String> = varchar("surname", 255)
    val email: Column<String> = varchar("email", 255)
    val type: Column<String> = varchar("type", 255).default("HUMAN")

    fun toWorker(raw: ResultRow): Worker {
        val worker = Worker(
            id = raw[id],
            name = raw[name],
            surname = raw[surname]
        )
        worker.email = raw[email]
        return worker
    }
}