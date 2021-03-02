package com.jetbrains.handson.httpapi

import com.github.mustachejava.DefaultMustacheFactory
import com.jetbrains.handson.httpapi.db.DatabaseFactory
import com.jetbrains.handson.httpapi.routes.registerSlaveRoutes
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.mustache.Mustache
import io.ktor.serialization.json
import org.jetbrains.exposed.sql.Database


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    DatabaseFactory.init()


    install(ContentNegotiation){
        json()
    }
    install(Mustache) {
        mustacheFactory = DefaultMustacheFactory("templates")
    }
    registerSlaveRoutes()
}
